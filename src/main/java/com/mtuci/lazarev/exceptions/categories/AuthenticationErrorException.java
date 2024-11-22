package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.AuthenticationException;

public class AuthenticationErrorException extends AuthenticationException {
    public AuthenticationErrorException(String msg) {
        super(msg);
    }
    public AuthenticationErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
