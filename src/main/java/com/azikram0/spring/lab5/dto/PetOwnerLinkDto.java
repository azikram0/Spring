package com.azikram0.spring.lab5.dto;

public record PetOwnerLinkDto(
        Integer petId,
        Integer ownerId,
        boolean primaryContact,
        String contactNote
) {
}
