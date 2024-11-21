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
@Table(name = "LicenseType")
public class LicenseType {
    @GeneratedValue
    @Id
    private Long id;

    private String name, description;
    private Integer default_duration;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "licenseType")
    private List<License> licenses;
}
