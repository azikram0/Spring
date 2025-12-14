package com.azikram0.spring.lab456.repository;

import com.azikram0.spring.lab456.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends JpaRepository<Pet, Integer>,
        CrudRepository<Pet, Integer> {
}