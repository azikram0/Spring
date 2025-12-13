package com.azikram0.spring.lab56.mapper;

import com.azikram0.spring.lab4.entity.Specialist;
import com.azikram0.spring.lab56.dto.SpecialistDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpecialistMapper {
    public Specialist map(SpecialistDto dto) {
        Specialist s = new Specialist();
        s.setFirstName(dto.firstName());
        s.setLastName(dto.lastName());
        s.setPhone(dto.phone());
        s.setEmail(dto.email());
        s.setSpecializationId(dto.specializationId());
        s.setCreatedAt(LocalDateTime.now());
        return s;
    }
}
