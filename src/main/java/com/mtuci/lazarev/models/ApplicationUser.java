package com.mtuci.lazarev.models;

import com.mtuci.lazarev.configuration.SecurityConfig;
import com.mtuci.lazarev.repositories.RoleRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    private ApplicationRole applicationRole;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<License> licenses_owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<License> licenses_user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<LicenseHistory> licenseHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Device> devices;
}
