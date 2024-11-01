package com.mtuci.lazarev.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @GeneratedValue
    private UUID id;

    private Date nowDate, activationDate, expirationDate;
    private long expiration;

    private UUID userID, deviceID;
    private boolean isBlockedLicence;
    private String digitalSignature;
}
