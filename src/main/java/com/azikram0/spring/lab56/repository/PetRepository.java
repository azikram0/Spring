package com.azikram0.spring.lab56.repository;

import com.azikram0.spring.lab4.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends JpaRepository<Pet, Integer>,
        CrudRepository<Pet, Integer> {
}