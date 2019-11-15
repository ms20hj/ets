package com.cms.ets.web.annotation;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.log.IOperateLogService;
import com.cms.ets.common.constant.DateConstant;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.enums.CodeEnum;
import com.cms.ets.common.exception.BusinessException;
import com.cms.ets.common.exception.SystemException;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mongo.log.OperateLog;
import com.cms.ets.model.mysql.authority.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志注解切面
 * @date 2019年11月15日14:21:52
 * @author cms
 */
@Aspect
@Component
public class OperationLogAspect {

    private static Log LOG = LogFactory.get(OperationLogAspect.class);

    @Reference
    private IOperateLogService operateLogService;

    public OperationLogAspect() {
        LOG.info("操作日志注解启动！！！");
    }

    @Pointcut("@annotation(com.cms.ets.web.annotation.OperationLog)")
    public void OperationLogAspect() {

    }

    @AfterReturning(returning = "rvt", value = "OperationLogAspect()")
    public void doAfterReturn(JoinPoint joinPoint, Object rvt) {
        try {
            Map<String, String> params = getControllerMethodAnnotation(joinPoint);
            HandleResult handleResult = (HandleResult) rvt;
            OperateLog log = this.createLog(params, handleResult);
            this.addContent(log, joinPoint);
            operateLogService.save(log);
        } catch (Throwable e) {
            LOG.error(e);
            if (e instanceof SystemException) {
                throw (SystemException) e;
            }
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }

        }

    }

    private OperateLog createLog(Map<String, String> params, HandleResult handleResult) {
        OperateLog log = new OperateLog();
        log.setAction(params.get("action"));
        log.setModuleName(params.get("moduleName"));
        log.setMenuName(params.get("menuName"));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        log.setUser(user);
        log.setCreateTime(DateUtil.format(new Date(), DateConstant.DF_yyyyMMddHHmmss));
        String result = handleResult.getCode() == CodeEnum.SUCCESS.getCode() ? OperateLogConstant.SUCCESS : OperateLogConstant.FAILURE;
        log.setResult(result);
        return log;
    }

    private void addContent(OperateLog log, JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 1) {
            Object param = joinPoint.getArgs()[0];
            log.setContent(JSONUtil.toJsonStr(param));
        } else if (joinPoint.getArgs().length > 1) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                Object param = joinPoint.getArgs()[i];
                map.put(param.getClass().getName(), param);
            }
            log.setContent(JSONUtil.toJsonStr(map));
        }
    }

    /**
     * 获取注解中对方法的描述信息
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private Map<String, String> getControllerMethodAnnotation(JoinPoint joinPoint) throws Exception {
        //获得执行方法的类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获得执行方法的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取切点方法的所有参数类型
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        //获取公共方法，不包括类私有的
        Method[] methods = targetClass.getMethods();
        Map<String,String> map = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                //对比方法中参数的个数
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    String moduleName = method.getAnnotation(OperationLog.class).moduleName();
                    String menuName = method.getAnnotation(OperationLog.class).menuName();
                    String action =  method.getAnnotation(OperationLog.class).action();
                    map.put("moduleName",moduleName);
                    map.put("menuName",menuName);
                    map.put("action",action);
                    break;
                }
            }
        }
        return map;
    }
}
