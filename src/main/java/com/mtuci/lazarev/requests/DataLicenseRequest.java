package com.mtuci.lazarev.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class DataLicenseRequest {
    private Long id, type_id, product_id, user_id, owner_id;
    private Date first_activation_date, ending_date;
    private boolean blocked;
    private Integer device_count;
    private Long duration;
    private String code, description;
}
