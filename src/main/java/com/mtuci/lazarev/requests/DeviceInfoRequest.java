package com.mtuci.lazarev.requests;

import lombok.Data;

@Data
public class DeviceInfoRequest {
    private String name, macAddress;
}
