package com.mtuci.lazarev.exceptions;

public abstract class LicenseTypeException extends RuntimeException {
    public LicenseTypeException(String msg) {
        super(msg);
    }
    public LicenseTypeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
