package com.mtuci.lazarev.requests;

import lombok.Data;

@Data
public class LicenseCreateRequest {
    private Long productId, ownerId, licenseTypeId;
    private Integer device_count;
    private Long duration;
}
