package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.LicenseTypeException;

public class LicenseTypeNotFoundException extends LicenseTypeException {
    public LicenseTypeNotFoundException(String msg) {
        super(msg);
    }
    public LicenseTypeNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
