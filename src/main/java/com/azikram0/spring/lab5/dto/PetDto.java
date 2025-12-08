package com.azikram0.spring.lab5.dto;

import java.time.LocalDate;

public record PetDto(
        int id,
        String name,
        String species,
        String breed,
        LocalDate birthDate,
        String gender
) {
}
