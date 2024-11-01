package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class ApplicationUser {

    public ApplicationUser(
            String username,
            String email,
            String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;
    private String email;

    @OneToOne
    @MapsId
    private Licence licence;

    @Enumerated(EnumType.STRING)
    private ApplicationRole role;
}
