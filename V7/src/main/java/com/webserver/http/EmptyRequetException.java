package com.webserver.http;

/**
 * 空请求异常
 * 当HttpRequest实例化时(解析请求的过程)遇到了空请求则会抛出该异常
 */
public class EmptyRequetException extends Exception{
    public EmptyRequetException() {
    }

    public EmptyRequetException(String message) {
        super(message);
    }

    public EmptyRequetException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequetException(Throwable cause) {
        super(cause);
    }

    public EmptyRequetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
