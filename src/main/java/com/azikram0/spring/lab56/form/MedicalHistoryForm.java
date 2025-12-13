package com.azikram0.spring.lab56.form;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicalHistoryForm(
        @NotNull(message = "ID питомца обязателен")
        Integer petId,

        @NotBlank(message = "Описание истории болезни не может быть пустым")
        String historyText,

        @NotNull(message = "Дата последнего визита обязательна")
        LocalDate lastVisitDate,

        @NotNull(message = "Дата последнего обновления обязательна")
        LocalDateTime lastUpdated,

        @NotNull(message = "ID специалиста обязателен")
        Integer specialistId
) {
    public MedicalHistoryForm() {
        this(null, "", null, null, null);
    }
}
