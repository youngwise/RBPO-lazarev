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

    private Date first_activation_date, ending_date;
    private boolean blocked;
    private Integer device_count, duration;
    private String code, description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private LicenseType licenseType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private ApplicationUser user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private ApplicationUser owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "license")
    private List<LicenseHistory> licenseHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "license")
    private List<DeviceLicense> deviceLicenses;

}
