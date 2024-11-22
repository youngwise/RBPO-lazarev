package com.mtuci.lazarev.requests;

import lombok.Data;

@Data
public class LicenseUpdateRequest {
    private String password, codeActivation;
}
