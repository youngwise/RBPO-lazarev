package com.mtuci.lazarev.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DataLicenseHistoryRequest {
    private Long id, license_id, user_id;
    private String status, description;
    private Date change_date;
}
