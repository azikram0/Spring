package com.azikram0.spring.lab56.restcontroller;

import com.azikram0.spring.lab56.dto.PetDto;
import com.azikram0.spring.lab56.service.PetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetRestController {
    public final PetService petService;

    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/add")
    public void addPet(PetDto petDto) {
        petService.addPet(petDto);
    }
}
