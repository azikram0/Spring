package com.azikram0.spring.lab456.dto;

public record SpecialistDto(
        int id,
        String firstName,
        String lastName,
        int specializationId,
        String phone,
        String email
) {
}

