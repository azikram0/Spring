package com.azikram0.spring.lab5.form;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PetForm(
        @NotBlank(message = "Имя не может быть пустым")
        String name,

        @NotBlank(message = "Вид животного обязателен")
        String species,

        @NotBlank(message = "Порода обязателена")
        String breed,

        @NotNull(message = "Дата рождения обязателена")
        LocalDate birthDate,

        @NotBlank(message = "Пол обязателен")
        String gender
) {}

