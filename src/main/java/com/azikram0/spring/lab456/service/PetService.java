package com.azikram0.spring.lab456.service;

import com.azikram0.spring.lab456.dto.PetDto;
import com.azikram0.spring.lab456.entity.MedicalHistory;
import com.azikram0.spring.lab456.entity.Owner;
import com.azikram0.spring.lab456.entity.Pet;
import com.azikram0.spring.lab456.entity.Specialist;
import com.azikram0.spring.lab456.mapper.PetMapper;
import com.azikram0.spring.lab456.repository.MedicalHistoryRepository;
import com.azikram0.spring.lab456.repository.PetRepository;
import com.azikram0.spring.lab456.repository.SpecialistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final SpecialistRepository specialistRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PetMapper petMapper;

    @Autowired
    public PetService(PetRepository petRepository,
                      SpecialistRepository specialistRepository,
                      MedicalHistoryRepository medicalHistoryRepository,
                      PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.specialistRepository = specialistRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public void addPet(PetDto petDto) {
        Pet p = petMapper.map(petDto);
        Specialist s = specialistRepository.findById(petDto.specialistId()).orElseThrow();
        p.setSpecialist(s);
        s.addPet(p);
        petRepository.save(p);
    }

    public void deletePet(PetDto petDto) {
        Pet p  = petRepository.findById(petDto.id()).orElseThrow();
        Specialist s = p.getSpecialist();
        if (s != null) {
            s.getPets().remove(p);
            p.setSpecialist(null);
        }
        petRepository.delete(p);
    }

    public void updatePet(PetDto petDto) {
        Pet p = petRepository.findById(petDto.id()).orElseThrow();
        p.setName(petDto.name());
        p.setBirthDate(petDto.birthDate());
        p.setGender(petDto.gender());
        p.setBreed(petDto.breed());
        p.setSpeciesId(petDto.speciesId());
        p.setSpecialist(specialistRepository.findById(petDto.specialistId()).orElseThrow());
        petRepository.save(p);
    }

    public List<String> getAllOwnersByPetid(Integer petid) {
        List<String> owners =  new ArrayList<>();
        for (Owner owner: petRepository.findById(petid).orElseThrow().getOwners()) {
            owners.add(owner.getFirstName() + " "  + owner.getLastName() + ", " + owner.getEmail());
        }
        return owners;
    }

    public Pet getPetById(Integer petId) {
        return petRepository.findById(petId).orElseThrow();
    }
}
