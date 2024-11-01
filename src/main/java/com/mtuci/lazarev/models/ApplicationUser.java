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
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    private ApplicationRole applicationRole;
}
