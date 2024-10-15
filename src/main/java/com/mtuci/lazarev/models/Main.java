package com.mtuci.lazarev.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "main")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Main {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "main")
    @JsonIgnoreProperties("main")
    private List<Details> details;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "main_info",
            joinColumns = @JoinColumn(name = "main_id"),
            inverseJoinColumns = @JoinColumn(name = "info_id")

    )
    private List<Info> infos;
}
