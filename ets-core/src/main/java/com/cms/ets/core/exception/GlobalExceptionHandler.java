package com.cms.ets.core.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.ets.common.enums.CodeEnum;
import com.cms.ets.common.exception.BusinessException;
import com.cms.ets.common.exception.SystemException;
import com.cms.ets.common.response.HandleResult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局 捕获异常 返回错误信息
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get(GlobalExceptionHandler.class);

    /**
     * 捕获异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    @ResponseBody
    public HandleResult respException(Exception e){
        e.printStackTrace();
        log.info(e.getMessage(),e.fillInStackTrace());
        return HandleResult.error();
    }
    
    /**
     * 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public HandleResult businessException(BusinessException e){
        log.info(e);
        return HandleResult.error(CodeEnum.BUSINESS_ERROR, e.getMessage());
    }
    
    /**
     * 系统异常处理
     * @return
     * @author mawei
     * @date 2019/4/30
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public HandleResult systemException(SystemException e){
        log.info(e);
        return HandleResult.error(CodeEnum.SYSTEM_ERROR, e.getMessage());
    }
    

    /**
     * shiro权限异常处理
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public HandleResult authorizationException(AuthorizationException e){
        log.info(e);
        return HandleResult.error(CodeEnum.AUTHORIZE_ERROR, e.getMessage());
    }

    /**
     * @Description: 校验参数 失败 返回处理
     * @MethodName:validatorException
     * @param [e]
     * @return com.linewell.idaas.model.HandleResult
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    @ResponseBody
    public HandleResult validatorException(Exception e){
        //Hibernate Validator验证消息返回
        BindingResult result = null;
        if (e instanceof MethodArgumentNotValidException){
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException){
            result = ((BindException) e).getBindingResult();
        }else if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.append(violation.getMessage()).append(",");
            }
            errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
            return HandleResult.error(CodeEnum.VALIDATOR_ERROR, errorMsg.toString());
        }
        if (result != null) {
            StringBuilder errorMsg = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                errorMsg.append(error.getDefaultMessage()).append(",");
            }
            errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
            return HandleResult.error(CodeEnum.VALIDATOR_ERROR,errorMsg);
        }
        return HandleResult.error(CodeEnum.VALIDATOR_ERROR, null);
    }

}
