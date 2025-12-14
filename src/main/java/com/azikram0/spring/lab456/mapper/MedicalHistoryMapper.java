package com.azikram0.spring.lab456.mapper;

import com.azikram0.spring.lab456.dto.MedicalHistoryDto;
import com.azikram0.spring.lab456.entity.MedicalHistory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MedicalHistoryMapper {
    public MedicalHistory map(MedicalHistoryDto medicalHistoryDto) {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setHistoryText(medicalHistoryDto.historyText());
        medicalHistory.setLastVisitDate(medicalHistoryDto.lastVisitDate());
        medicalHistory.setLastUpdated(LocalDateTime.now());
        medicalHistory.setCreatedBySpecialist(medicalHistoryDto.specialistId());
        return medicalHistory;
    }
}
