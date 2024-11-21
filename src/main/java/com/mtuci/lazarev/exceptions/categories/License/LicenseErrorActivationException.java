package com.mtuci.lazarev.exceptions.categories.License;

import com.mtuci.lazarev.exceptions.LicenseException;

public class LicenseErrorActivationException extends LicenseException {
    public LicenseErrorActivationException(String msg) {
        super(msg);
    }
    public LicenseErrorActivationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
