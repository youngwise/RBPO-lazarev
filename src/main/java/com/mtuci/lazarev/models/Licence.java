package com.mtuci.lazarev.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "licence")
public class Licence {
    @Id
    @GeneratedValue
    private UUID id;

    private String key, name, device_id;
    private Date date_creation, date_expiration;
    private boolean status;
}
