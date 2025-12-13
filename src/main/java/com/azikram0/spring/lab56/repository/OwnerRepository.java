package com.azikram0.spring.lab56.repository;

import com.azikram0.spring.lab4.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer>,
        CrudRepository<Owner, Integer> {
}
