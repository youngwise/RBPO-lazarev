package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Device")
public class Device {
    @GeneratedValue
    @Id
    private Long id;

    private String name, mac_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ApplicationUser user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
    private List<DeviceLicense> deviceLicenses;
}
