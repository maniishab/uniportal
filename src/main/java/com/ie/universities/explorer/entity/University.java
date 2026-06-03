package com.ie.universities.explorer.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "stateProvince")
    private String stateProvince;
    @Column(name = "alphaTwoCode")
    private String alphaTwoCode;
    @Column(name = "domains")
    private String[] domains;
    @Column(name = "webPages")
    private String[] webPages;
}
