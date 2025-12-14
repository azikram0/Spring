package com.azikram0.spring.lab456.form;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PetForm(
        @NotBlank(message = "Имя не может быть пустым")
        String name,

        @NotBlank(message = "Вид животного обязателен")
        Integer speciesId,

        @NotBlank(message = "Порода обязательна")
        String breed,

        @NotNull(message = "Дата рождения обязательна")
        LocalDate birthDate,

        @NotBlank(message = "Пол обязателен")
        String gender
) {
    public PetForm() {
        this("", null, "", null, "");
    }
}

