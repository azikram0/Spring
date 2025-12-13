package com.azikram0.spring.lab56.service;

import com.azikram0.spring.lab56.dto.SpecialistDto;
import com.azikram0.spring.lab56.mapper.SpecialistMapper;
import com.azikram0.spring.lab56.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SpecialistMapper specialistMapper;

    @Autowired
    public SpecialistService(SpecialistRepository specialistRepository, SpecialistMapper specialistMapper) {
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
    }

    public void addSpecialist(SpecialistDto specialistDto) {
        specialistRepository.save(specialistMapper.map(specialistDto));
    }
}
