package com.mtuci.lazarev.exceptions.categories.License;

import com.mtuci.lazarev.exceptions.LicenseException;

public class LicenseNotFoundException extends LicenseException {
    public LicenseNotFoundException(String msg) {
        super(msg);
    }
    public LicenseNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
