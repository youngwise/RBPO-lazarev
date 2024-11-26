package com.mtuci.lazarev.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class DataDeviceLicenseRequest {
    private Long id, device_id, license_id;
    private Date activation_date;
}
