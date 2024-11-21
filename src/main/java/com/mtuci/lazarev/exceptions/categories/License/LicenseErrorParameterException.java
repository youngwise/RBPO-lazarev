package com.mtuci.lazarev.exceptions.categories.License;

import com.mtuci.lazarev.exceptions.LicenseException;

public class LicenseErrorParameterException extends LicenseException {
    public LicenseErrorParameterException(String msg) {
        super(msg);
    }
    public LicenseErrorParameterException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
