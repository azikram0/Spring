package com.azikram0.spring.lab456.dto;

public record PetOwnerLinkDto(
        Integer petId,
        Integer ownerId,
        boolean primaryContact,
        String contactNote
) {
}
