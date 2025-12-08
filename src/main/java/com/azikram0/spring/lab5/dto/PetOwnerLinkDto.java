package com.azikram0.spring.lab5.dto;

import jakarta.validation.constraints.NotNull;

public record PetOwnerLinkDto(
        @NotNull(message = "ID питомца обязателен")
        Integer petId,

        @NotNull(message = "ID владельца обязателен")
        Integer ownerId,

        boolean primaryContact,

        String contactNote
) {}
