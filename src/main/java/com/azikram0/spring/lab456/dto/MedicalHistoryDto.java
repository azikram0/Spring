package com.azikram0.spring.lab456.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicalHistoryDto(
        int petId,
        String historyText,
        LocalDate lastVisitDate,
        LocalDateTime lastUpdated,
        int specialistId
) {
}
