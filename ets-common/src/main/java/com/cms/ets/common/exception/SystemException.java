package com.cms.ets.common.exception;

/**
 * 系统异常
 * @date 2019年7月22日17:43:21
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 8700061912062643410L;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
