package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DeviceLicense")
public class DeviceLicense {
    @GeneratedValue
    @Id
    private Long id;
    private Date activation_date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "license_id")
    private License license;
}
