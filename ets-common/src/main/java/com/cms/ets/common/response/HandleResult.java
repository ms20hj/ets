package com.cms.ets.common.response;

import com.cms.ets.common.enums.CodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 通用请求返回结果对象类
 * @date 2019年7月22日16:53:50
 * @author cms
 */
public class HandleResult implements Serializable {
    private static final long serialVersionUID = 5223691994103445436L;

    /**
     * 是否成功
     */
    private boolean status;
    /**
     * 消息码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 数据内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static HandleResult success(){
        HandleResult resp = new HandleResult();
        resp.setCode(CodeEnum.SUCCESS.getCode());
        resp.setMessage(CodeEnum.SUCCESS.getMessage());
        resp.setStatus(true);
        return resp;
    }

    public static HandleResult success(Object data){
        HandleResult resp = new HandleResult();
        resp.setCode(CodeEnum.SUCCESS.getCode());
        resp.setMessage(CodeEnum.SUCCESS.getMessage());
        resp.setData(data);
        resp.setStatus(true);
        return resp;
    }

    public static HandleResult error(){
        HandleResult resp = new HandleResult();
        resp.setCode(CodeEnum.FAILURE.getCode());
        resp.setMessage(CodeEnum.FAILURE.getMessage());
        resp.setStatus(false);
        return resp;
    }

    public static HandleResult error(CodeEnum ce){
        HandleResult resp = new HandleResult();
        resp.setCode(ce.getCode());
        resp.setMessage(ce.getMessage());
        resp.setStatus(false);
        return resp;
    }

    public static HandleResult error(CodeEnum ce, Object data){
        HandleResult resp = new HandleResult();
        resp.setCode(ce.getCode());
        resp.setMessage(ce.getMessage());
        resp.setData(data);
        resp.setStatus(false);
        return resp;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
