package com.azikram0.spring.lab56.dto;

public record OwnerDto(
        int id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String address
) {
}

