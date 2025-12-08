package com.azikram0.spring.lab5.dto;

import jakarta.validation.constraints.*;

public record SpecialistDto(
        @NotNull(message = "ID специалиста обязателен")
        Integer id,

        @NotBlank(message = "Имя не может быть пустым")
        String firstName,

        @NotBlank(message = "Фамилия не может быть пустой")
        String lastName,

        @NotBlank(message = "Квалификация обязателена")
        String qualification,

        @Pattern(regexp = "\\+?\\d{10,15}", message = "Неверный формат телефона")
        String phone,

        @Email(message = "Некорректный email")
        String email
) {}
