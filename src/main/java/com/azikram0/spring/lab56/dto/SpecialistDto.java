package com.azikram0.spring.lab56.dto;

public record SpecialistDto(
        Integer id,
        String firstName,
        String lastName,
        Integer specializationId,
        String phone,
        String email
) {
}

