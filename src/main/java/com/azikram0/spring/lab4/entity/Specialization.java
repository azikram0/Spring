package com.azikram0.spring.lab4.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "specialization_id")
    private List<Specialist> specialists;
}
