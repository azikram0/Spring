package com.azikram0.spring.lab456.service;

import com.azikram0.spring.lab456.dto.PetOwnerLinkDto;
import com.azikram0.spring.lab456.entity.PetOwnerLink;
import com.azikram0.spring.lab456.repository.PetOwnerLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PetOwnerLinkService {
    PetOwnerLinkRepository petOwnerLinkRepository;

    @Autowired
    public PetOwnerLinkService(PetOwnerLinkRepository petOwnerLinkRepository) {
        this.petOwnerLinkRepository = petOwnerLinkRepository;
    }

    public void addLink(PetOwnerLinkDto petOwnerLinkDto) {
        PetOwnerLink petOwnerLink = new PetOwnerLink();
        petOwnerLink.setId(new PetOwnerLink.PetOwnerLinkId(petOwnerLinkDto.petId(), petOwnerLinkDto.ownerId()));
        petOwnerLink.setPrimaryContact(petOwnerLinkDto.primaryContact());
        petOwnerLink.setContactNote(petOwnerLinkDto.contactNote());
        petOwnerLink.setAddedAt(LocalDateTime.now());
        petOwnerLinkRepository.save(petOwnerLink);
    }

    public void deleteLink(PetOwnerLinkDto petOwnerLinkDto) {
        PetOwnerLink.PetOwnerLinkId id =
                new PetOwnerLink.PetOwnerLinkId(
                        petOwnerLinkDto.petId(),
                        petOwnerLinkDto.ownerId());

        petOwnerLinkRepository.deleteById(id);
    }

    public void updateLink(PetOwnerLinkDto petOwnerLinkDto, Integer oldPetId, Integer oldOwnerId) {
        PetOwnerLink.PetOwnerLinkId oldId =
                new PetOwnerLink.PetOwnerLinkId(oldPetId, oldOwnerId);
        PetOwnerLink existingLink = petOwnerLinkRepository.findById(oldId).orElseThrow();

        PetOwnerLink.PetOwnerLinkId newId =
                new PetOwnerLink.PetOwnerLinkId(
                        petOwnerLinkDto.petId(),
                        petOwnerLinkDto.ownerId());

        PetOwnerLink newLink = new PetOwnerLink();
        newLink.setId(newId);
        newLink.setPrimaryContact(petOwnerLinkDto.primaryContact());
        newLink.setContactNote(petOwnerLinkDto.contactNote());
        newLink.setAddedAt(LocalDateTime.now());

        petOwnerLinkRepository.save(newLink);
        petOwnerLinkRepository.delete(existingLink);
    }

}
