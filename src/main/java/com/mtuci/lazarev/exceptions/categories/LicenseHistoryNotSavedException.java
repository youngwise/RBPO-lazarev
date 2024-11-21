package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.LicenseHistoryException;

public class LicenseHistoryNotSavedException extends LicenseHistoryException {
    public LicenseHistoryNotSavedException(String msg) { super(msg); }
    public LicenseHistoryNotSavedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
