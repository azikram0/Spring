package com.azikram0.spring.lab456.repository;

import com.azikram0.spring.lab456.entity.PetOwnerLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PetOwnerLinkRepository extends JpaRepository<PetOwnerLink, PetOwnerLink.PetOwnerLinkId>,
        CrudRepository<PetOwnerLink, PetOwnerLink.PetOwnerLinkId> {
}
