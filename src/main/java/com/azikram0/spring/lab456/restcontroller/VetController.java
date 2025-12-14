package com.azikram0.spring.lab456.restcontroller;

import com.azikram0.spring.lab456.service.VetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vet")
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/all-owners")
    public List<String> getAllOwners() {
        return vetService.getAllOwners();
    }

    @GetMapping("/all-pets")
    public List<String> getAllPets() {
        return vetService.getAllPets();
    }

    @GetMapping("/all-specialists")
    public List<String> getAllSpecialists() {
        return vetService.getAllSpecialists();
    }

    @GetMapping("/all-species")
    public List<String> getAllSpecies() {
        return vetService.getAllSpecies();
    }

    @GetMapping("/all-specialization")
    public List<String> getAllSpecialization() {
        return vetService.getAllSpecialization();
    }

    @GetMapping("/all-medical-histories")
    public List<String> getAllMedicalHistories() {
        return vetService.getAllMedicalHistories();
    }
}
