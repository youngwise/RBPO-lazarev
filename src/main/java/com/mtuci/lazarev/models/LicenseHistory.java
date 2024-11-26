package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LicenseHistory")
public class LicenseHistory {
    @GeneratedValue
    @Id
    private Long id;

    private String status;

    @Column(length = 500)
    private String description;
    private Date change_date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "license_id")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private ApplicationUser user;
}
