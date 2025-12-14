package com.azikram0.spring.lab456.form;

import jakarta.validation.constraints.*;

public record SpecialistForm(
        @NotBlank(message = "Имя не может быть пустым")
        String firstName,

        @NotBlank(message = "Фамилия не может быть пустой")
        String lastName,

        @NotBlank(message = "Квалификация обязательна")
        Integer specializationId,

        @Pattern(regexp = "\\+?\\d{10,15}", message = "Неверный формат телефона")
        String phone,

        @Email(message = "Некорректный email")
        String email
) {
    public SpecialistForm() {
        this("", "", null, "", "");
    }
}
