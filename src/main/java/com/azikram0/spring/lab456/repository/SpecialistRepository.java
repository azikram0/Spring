package com.azikram0.spring.lab456.repository;

import com.azikram0.spring.lab456.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Integer>,
        CrudRepository<Specialist, Integer> {
}
