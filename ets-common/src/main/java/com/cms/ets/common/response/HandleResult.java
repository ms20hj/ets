package com.cms.ets.common.response;

import com.cms.ets.common.enums.CodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 通用请求返回结果对象类
 * @date 2019年7月22日16:53:50
 * @author cms
 */
public class HandleResult<T> implements Serializable {
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
    private T data;

    public static <T> HandleResult<T> success(){
        HandleResult<T> resp = new HandleResult<T>();
        resp.setCode(CodeEnum.SUCCESS.getCode());
        resp.setMessage(CodeEnum.SUCCESS.getMessage());
        resp.setStatus(true);
        return resp;
    }

    public static <T> HandleResult<T> success(T data){
        HandleResult<T> resp = new HandleResult<T>();
        resp.setCode(CodeEnum.SUCCESS.getCode());
        resp.setMessage(CodeEnum.SUCCESS.getMessage());
        resp.setData(data);
        resp.setStatus(true);
        return resp;
    }

    public static <T> HandleResult<T> error(){
        HandleResult<T> resp = new HandleResult<T>();
        resp.setCode(CodeEnum.FAILURE.getCode());
        resp.setMessage(CodeEnum.FAILURE.getMessage());
        resp.setStatus(true);
        return resp;
    }

    public static <T> HandleResult<T> error(CodeEnum ce){
        HandleResult<T> resp = new HandleResult<T>();
        resp.setCode(ce.getCode());
        resp.setMessage(ce.getMessage());
        resp.setStatus(true);
        return resp;
    }

    public static <T> HandleResult<T> error(CodeEnum ce, T data){
        HandleResult<T> resp = new HandleResult<T>();
        resp.setCode(ce.getCode());
        resp.setMessage(ce.getMessage());
        resp.setData(data);
        resp.setStatus(true);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
