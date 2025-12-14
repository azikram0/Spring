package com.azikram0.spring.lab456.repository;

import com.azikram0.spring.lab456.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer>,
        CrudRepository<MedicalHistory, Integer> {
}
