package com.azikram0.spring.lab456.service;

import com.azikram0.spring.lab456.dto.OwnerDto;
import com.azikram0.spring.lab456.dto.PetDto;
import com.azikram0.spring.lab456.dto.PetOwnerLinkDto;
import com.azikram0.spring.lab456.entity.Owner;
import com.azikram0.spring.lab456.entity.Pet;
import com.azikram0.spring.lab456.entity.PetOwnerLink;
import com.azikram0.spring.lab456.mapper.OwnerMapper;
import com.azikram0.spring.lab456.repository.OwnerRepository;
import com.azikram0.spring.lab456.repository.PetOwnerLinkRepository;
import com.azikram0.spring.lab456.repository.PetRepository;
import com.azikram0.spring.lab456.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public OwnerService(OwnerMapper ownerMapper, OwnerRepository ownerRepository, PetRepository petRepository, PetOwnerLinkRepository petOwnerLinkRepository, SpeciesRepository speciesRepository) {
        this.ownerMapper = ownerMapper;
        this.ownerRepository = ownerRepository;
        this.speciesRepository = speciesRepository;
    }

    public void addOwner(OwnerDto ownerDto) {
        ownerRepository.save(ownerMapper.map(ownerDto));
    }

    public void deleteOwner(OwnerDto ownerDto) {
        Owner owner = ownerRepository.findById(ownerDto.id()).orElseThrow();
        ownerRepository.delete(owner);
    }

    public void updateOwner(OwnerDto ownerDto) {
        Owner owner = ownerRepository.findById(ownerDto.id()).orElseThrow();
        owner.setFirstName(ownerDto.firstName());
        owner.setLastName(ownerDto.lastName());
        owner.setPhone(ownerDto.phone());
        owner.setEmail(ownerDto.email());
        owner.setAddress(ownerDto.address());
        ownerRepository.save(owner);
    }

    public List<String> getAllPetsByOwnerId(Integer ownerId) {
        List<String> pets = new ArrayList<>();
        for (Pet pet: ownerRepository.findById(ownerId).orElseThrow().getPets()) {
            pets.add(pet.getName() + ", " + speciesRepository.findById(pet.getSpeciesId()).get().getName()
                    + ", " + pet.getBreed());
        }
        return pets;
    }

    public Owner getOwnerById(Integer ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow();
    }
}
