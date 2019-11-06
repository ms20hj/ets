package com.cms.ets.web.controller;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.ets.common.enums.CodeEnum;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.authority.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 基础controller
 */
public abstract class BaseController {

	protected Log logger = LogFactory.get();

    /**
     * @Description: 成功信息返回
     * @MethodName:success
     * @param [data]
     */
    protected HandleResult success(Object data){
        return HandleResult.success(data);
    }

    protected HandleResult success(){
        return HandleResult.success();
    }

    /**
     * @Description: 错误信息返回
     * @MethodName:respError
     * @param []
     */
    protected HandleResult error(){
        return HandleResult.error();
    }

    protected HandleResult error(String message) {
        return HandleResult.error(message);
    }
    /**
     * @Description: 错误信息返回 根据枚举类型
     * @MethodName:respError
     * @param [codeEnum]
     */
    protected HandleResult error(CodeEnum codeEnum){
        return HandleResult.error(codeEnum);
    }


    public static HandleResult error(CodeEnum codeEnum, Object data){
    	return HandleResult.error(codeEnum, data);
    }

    /**
     * 返回对应的成功或者失败对象
     * @param flag 是否成功
     * @param data 数据
     * @return HandleResult
     * @date 2019年9月9日15:48:12
     * @author cms
     */
    protected HandleResult verifyResp(boolean flag){
        if (flag) {
            return HandleResult.success();
        } else {
            return HandleResult.error();
        }
    }

    /**
     * 获取当前登录用户对象信息
     * @return User
     * @date 2019年5月20日15:17:34
     * @author cms
     */
    protected User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return null;
        }
        return (User)subject.getPrincipal();
    }

}
