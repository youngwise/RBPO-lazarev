package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.UserException;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
