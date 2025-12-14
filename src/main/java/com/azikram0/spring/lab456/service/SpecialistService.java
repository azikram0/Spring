package com.azikram0.spring.lab456.service;

import com.azikram0.spring.lab456.dto.SpecialistDto;
import com.azikram0.spring.lab456.entity.MedicalHistory;
import com.azikram0.spring.lab456.entity.Pet;
import com.azikram0.spring.lab456.entity.Specialist;
import com.azikram0.spring.lab456.mapper.SpecialistMapper;
import com.azikram0.spring.lab456.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SpecialistMapper specialistMapper;
    private final SpecializationRepository specializationRepository;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpecialistService(SpecialistRepository specialistRepository,
                             SpecialistMapper specialistMapper,
                             SpecializationRepository specializationRepository,
                             MedicalHistoryRepository medicalHistoryRepository,
                             SpeciesRepository speciesRepository) {
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
        this.specializationRepository = specializationRepository;
        this.speciesRepository = speciesRepository;
    }

    public void addSpecialist(SpecialistDto specialistDto) {
        specialistRepository.save(specialistMapper.map(specialistDto));
    }

    public void deleteSpecialist(SpecialistDto specialistDto) {
        Specialist s = specialistRepository.findById(specialistDto.id()).orElseThrow();
        for (Pet pet : s.getPets()) {
            pet.setSpecialist(null);
        }
        for (MedicalHistory history : s.getMedicalHistories()) {
            history.setSpecialist(null);
        }
        specialistRepository.delete(s);
    }

    public void updateSpecialist(SpecialistDto specialistDto) {
        Specialist s = specialistRepository.findById(specialistDto.id()).orElseThrow();
        s.setFirstName(specialistDto.firstName());
        s.setLastName(specialistDto.lastName());
        s.setPhone(specialistDto.phone());
        s.setEmail(specialistDto.email());
        s.setSpecialization(specializationRepository.findById(specialistDto.specializationId()).orElseThrow());
        specialistRepository.save(s);
    }

    public List<String> getAllPetsBySpecialistId(Integer specialistId) {
        List<String> pets = new ArrayList<>();
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow();
        for (Pet pet: specialist.getPets()) {
            pets.add(pet.getName() + ", "
                    + speciesRepository.findById(pet.getSpeciesId()).get().getName()
                    + ", " + pet.getBreed());
        }
        return  pets;
    }

    public Specialist getSpecialistById(Integer specialistId) {
        return specialistRepository.findById(specialistId).orElseThrow();
    }
}
