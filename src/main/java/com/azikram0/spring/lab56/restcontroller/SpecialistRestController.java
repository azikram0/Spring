package com.azikram0.spring.lab56.restcontroller;

import com.azikram0.spring.lab56.dto.SpecialistDto;
import com.azikram0.spring.lab56.service.SpecialistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specialists")
public class SpecialistRestController {
    private final SpecialistService specialistService;

    public SpecialistRestController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @PostMapping("/add")
    public void addSpecialist(SpecialistDto specialistDto) {
        specialistService.addSpecialist(specialistDto);
    }
}
