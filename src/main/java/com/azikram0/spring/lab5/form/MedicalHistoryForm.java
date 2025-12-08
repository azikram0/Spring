package com.azikram0.spring.lab5.form;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicalHistoryForm(
        @NotNull(message = "ID питомца обязателен")
        Integer petId,

        @NotBlank(message = "Описание истории болезни не может быть пустым")
        String historyText,

        @NotNull(message = "Дата последнего визита обязателена")
        LocalDate lastVisitDate,

        @NotNull(message = "Дата последнего обновления обязателена")
        LocalDateTime lastUpdated,

        @NotNull(message = "ID специалиста обязателен")
        Integer specialistId
) {}
