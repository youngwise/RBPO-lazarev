package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.DeviceLicenseException;

public class DeviceLicenseNotFoundException extends DeviceLicenseException {
    public DeviceLicenseNotFoundException(String msg) { super(msg); }
    public DeviceLicenseNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
