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
@Table(name = "Trial")
public class Trial {
    @GeneratedValue
    @Id
    private Long id;
    private Date start_date, endDate;

    @OneToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ApplicationUser user;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
