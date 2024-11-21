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
@Table(name = "Users")
public class ApplicationUser {
    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true)
    private String login;

    private String password_hash;

    @Column(unique = true)
    private String email;

    private ApplicationRole role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<License> licenses_owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<License> licenses_user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<LicenseHistory> licenseHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Device> devices;
}
