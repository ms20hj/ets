package com.cms.ets.common.enums;

public enum CodeEnum {

    SUCCESS("request success", 0),
    FAILURE("request failure", 1),
    BUSINESS_ERROR("业务处理异常", 10001),
    SYSTEM_ERROR("系统异常", 10002),
    AUTHORIZE_ERROR("没有权限", 10003),
    VALIDATOR_ERROR("校验失败", 10004);




    CodeEnum(String message, int code){
        this.message = message;
        this.code = code;
    }

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
