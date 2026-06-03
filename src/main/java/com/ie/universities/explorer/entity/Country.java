package com.ie.universities.explorer.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "iso", nullable = false, length = 2)
    private String iso;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "nicename", nullable = false, length = 80)
    private String nicename;

    @Column(name = "iso3", length = 3)
    private String iso3;

    @Column(name = "numcode")
    private Short numcode;

    @Column(name = "phonecode", nullable = false)
    private Integer phonecode;
}
