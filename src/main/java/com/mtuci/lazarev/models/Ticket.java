package com.mtuci.lazarev.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Ticket {
    private Date nowDate, activationDate, expirationDate;
    private Long expiration;
    private Long userID;
    private String device;
    private boolean isBlockedLicence;
    private String digitalSignature;
    private String description;
    private String productName;
    private String licenseType;
}
