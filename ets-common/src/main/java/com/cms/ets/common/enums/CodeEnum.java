package com.cms.ets.common.enums;

public enum CodeEnum {
    /**
     * 通用请求成功
     */
    SUCCESS("请求成功", 0),
    /**
     * 通用请求失败
     */
    FAILURE("请求失败", 1),
    /**
     * 业务处理异常
     */
    BUSINESS_ERROR("业务处理异常", 10001),
    /**
     * 系统bug
     */
    SYSTEM_ERROR("系统异常", 10002),
    /**
     * 无权限
     */
    NO_AUTHORIZE("没有权限", 10003),
    /**
     * 校验失败
     */
    VALIDATOR_ERROR("校验失败", 10004),
    /**
     * 查无数据
     */
    QUERY_NO_DATA("无对应数据", 10005),
    /**
     * 登录失效/未登录
     */
    AUTHORIZE_UNAUTH("未登录", 10006);



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
