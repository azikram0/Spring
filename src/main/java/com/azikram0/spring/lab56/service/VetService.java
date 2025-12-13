package com.azikram0.spring.lab56.service;

import com.azikram0.spring.lab4.entity.*;
import com.azikram0.spring.lab56.mapper.PetMapper;
import com.azikram0.spring.lab56.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VetService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final SpecialistRepository specialistRepository;
    private final SpecializationRepository specializationRepository;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public VetService(
            OwnerRepository ownerRepository,
            PetRepository petRepository,
            SpecialistRepository specialistRepository,
            SpecializationRepository specializationRepository,
            SpeciesRepository speciesRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.specialistRepository = specialistRepository;
        this.specializationRepository = specializationRepository;
        this.speciesRepository = speciesRepository;
    }

    public List<String> getAllOwners() {
        List<String> owners = new ArrayList<>();
        for (Owner owner : ownerRepository.findAll()) {
            owners.add(owner.getFirstName() + " "  + owner.getLastName() + ", " + owner.getEmail());
        }
        return owners;
    }

    public List<String> getAllPets() {
        List<String> pets = new ArrayList<>();
        for (Pet pet : petRepository.findAll()) {
            pets.add(pet.getName() + ", " + speciesRepository.findById(pet.getSpeciesId()) + ", " + pet.getBreed());
        }
        return pets;
    }

    public List<String> getAllSpecialists() {
        List<String> specialists = new ArrayList<>();
        for (Specialist specialist : specialistRepository.findAll()) {
            specialists.add(specialist.getFirstName() + " "
                    + specialist.getLastName() + ", "
                    + specializationRepository.findById(specialist.getSpecializationId()));
        }
        return specialists;
    }

    public List<String> getAllSpecies() {
        return speciesRepository.findAll().stream().map(Species::getName).toList();
    }

    public List<String> getAllSpecialization() {
        return specializationRepository.findAll().stream().map(Specialization::getName).toList();
    }
}
