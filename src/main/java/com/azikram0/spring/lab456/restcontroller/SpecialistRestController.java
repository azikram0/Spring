package com.azikram0.spring.lab456.restcontroller;

import com.azikram0.spring.lab456.dto.SpecialistDto;
import com.azikram0.spring.lab456.entity.Pet;
import com.azikram0.spring.lab456.entity.Specialist;
import com.azikram0.spring.lab456.service.SpecialistService;
import com.azikram0.spring.lab456.service.VetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vet/specialists")
public class SpecialistRestController {
    private final SpecialistService specialistService;

    public SpecialistRestController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @PostMapping("/add")
    public void addSpecialist(@RequestBody SpecialistDto specialistDto) {
        specialistService.addSpecialist(specialistDto);
    }

    @DeleteMapping("/delete")
    public void deleteSpecialist(@RequestBody SpecialistDto specialistDto) {
        specialistService.deleteSpecialist(specialistDto);
    }

    @PutMapping("/update")
    public void updateSpecialist(@RequestBody SpecialistDto specialistDto) {
        specialistService.updateSpecialist(specialistDto);
    }

    @GetMapping("/pets/get")
    public List<String> getAllPetsBySpecialistId(@RequestParam Integer specialistId) {
        return specialistService.getAllPetsBySpecialistId(specialistId);
    }

    @GetMapping("/info")
    public Specialist getSpecialistById(@RequestParam Integer specialistId) {
        return specialistService.getSpecialistById(specialistId);
    }
}
