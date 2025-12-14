package com.azikram0.spring.lab456.service;

import com.azikram0.spring.lab456.dto.MedicalHistoryDto;
import com.azikram0.spring.lab456.entity.MedicalHistory;
import com.azikram0.spring.lab456.entity.Pet;
import com.azikram0.spring.lab456.mapper.MedicalHistoryMapper;
import com.azikram0.spring.lab456.repository.MedicalHistoryRepository;
import com.azikram0.spring.lab456.repository.PetRepository;
import com.azikram0.spring.lab456.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final SpecialistRepository  specialistRepository;
    private final PetRepository  petRepository;
    private final MedicalHistoryMapper medicalHistoryMapper;

    @Autowired
    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository,
                                 SpecialistRepository specialistRepository,
                                 PetRepository petRepository,
                                 MedicalHistoryMapper medicalHistoryMapper) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.specialistRepository = specialistRepository;
        this.petRepository = petRepository;
        this.medicalHistoryMapper = medicalHistoryMapper;
    }

    public void addMedicalHistory(MedicalHistoryDto medicalHistoryDto) {
        MedicalHistory mh = medicalHistoryMapper.map(medicalHistoryDto);
        Pet p = petRepository.findById(medicalHistoryDto.petId()).orElseThrow();
        mh.setPet(p);
        p.setMedicalHistory(mh);
        medicalHistoryRepository.save(mh);
    }

    public void deleteMedicalHistory(MedicalHistoryDto medicalHistoryDto) {
        MedicalHistory mh = medicalHistoryRepository.findById(medicalHistoryDto.petId()).orElseThrow();
        Pet p = mh.getPet();
        p.setMedicalHistory(null);
        medicalHistoryRepository.delete(mh);
    }

    public void updateMedicalHistory(MedicalHistoryDto medicalHistoryDto) {
        MedicalHistory mh = medicalHistoryRepository.findById(medicalHistoryDto.petId()).orElseThrow();
        Pet p = mh.getPet();
        mh.setPet(p);
        mh.setSpecialist(specialistRepository.getReferenceById(medicalHistoryDto.specialistId()));
        mh.setHistoryText(medicalHistoryDto.historyText());
        mh.setLastVisitDate(medicalHistoryDto.lastVisitDate());
        p.setMedicalHistory(mh);
        medicalHistoryRepository.save(mh);
    }

    public MedicalHistory getMedicalHistoryById(Integer medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId).orElseThrow();
    }
}
