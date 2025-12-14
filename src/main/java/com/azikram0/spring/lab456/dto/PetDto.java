package com.azikram0.spring.lab456.dto;

import java.time.LocalDate;

public record PetDto(
        int id,
        String name,
        int speciesId,
        String breed,
        LocalDate birthDate,
        String gender,
        int specialistId
) {
}
