package com.mtuci.lazarev.exceptions;

public abstract class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg) {
        super(msg);
    }
    public AuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
