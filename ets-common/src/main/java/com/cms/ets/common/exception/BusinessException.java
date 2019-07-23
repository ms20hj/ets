package com.cms.ets.common.exception;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 5355537547978582956L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
