package com.azikram0.spring.lab56.repository;

import com.azikram0.spring.lab4.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer>,
        CrudRepository<Specialization, Integer> {
}
