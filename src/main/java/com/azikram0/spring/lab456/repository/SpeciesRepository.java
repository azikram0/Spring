package com.azikram0.spring.lab456.repository;

import com.azikram0.spring.lab456.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpeciesRepository extends JpaRepository<Species, Integer>,
        CrudRepository<Species, Integer> {
}
