package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "License")
public class License {
    @Id
    @GeneratedValue
    private Long id;

    private Date activationDate, endingDate;
    private boolean blocked;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private LicenseType licenseType;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private List<Device> device;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id", nullable = false)
    private ActivationCode activationCode;
}
