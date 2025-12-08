package com.azikram0.spring.lab5.dto;

public record SpecialistDto(
        Integer id,
        String firstName,
        String lastName,
        String qualification,
        String phone,
        String email
) {
}

