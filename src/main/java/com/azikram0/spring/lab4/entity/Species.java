package com.azikram0.spring.lab4.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "species_id")
    private List<Pet> pets;
}
