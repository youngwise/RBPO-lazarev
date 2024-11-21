package com.mtuci.lazarev.requests;

import lombok.Data;

@Data
public class DeviceRequest {
    private String activationCode, name, mac_address;
}
