package com.azikram0.spring.lab456.repository;

import com.azikram0.spring.lab456.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer>,
        CrudRepository<Specialization, Integer> {
}
