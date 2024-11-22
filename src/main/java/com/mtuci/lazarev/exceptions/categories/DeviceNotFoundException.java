package com.mtuci.lazarev.exceptions.categories;

import com.mtuci.lazarev.exceptions.DeviceException;

public class DeviceNotFoundException extends DeviceException {
    public DeviceNotFoundException(String msg) { super(msg); }
    public DeviceNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
