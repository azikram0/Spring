package com.azikram0.spring.lab56.repository;

import com.azikram0.spring.lab4.entity.Pet;
import com.azikram0.spring.lab4.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Integer>,
        CrudRepository<Specialist, Integer> {
}
