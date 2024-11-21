package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean is_blocked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<License> licenses;
}
