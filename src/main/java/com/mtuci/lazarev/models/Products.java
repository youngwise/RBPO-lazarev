package com.mtuci.lazarev.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;

import java.util.UUID;

@Data
public class Products {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private boolean available;
}
