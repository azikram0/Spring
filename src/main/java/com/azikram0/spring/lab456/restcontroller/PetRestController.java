package com.azikram0.spring.lab456.restcontroller;

import com.azikram0.spring.lab456.dto.PetDto;
import com.azikram0.spring.lab456.entity.Pet;
import com.azikram0.spring.lab456.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vet/pets")
public class PetRestController {
    public final PetService petService;

    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/add")
    public void addPet(@RequestBody PetDto petDto) {
        petService.addPet(petDto);
    }

    @DeleteMapping("/delete")
    public void deletePet(@RequestBody PetDto petDto) {
        petService.deletePet(petDto);
    }

    @PutMapping("/update")
    public void updatePet(@RequestBody PetDto petDto) {
        petService.updatePet(petDto);
    }

    @GetMapping("/owners/get")
    public List<String> getAllOwnersByPetId(@RequestParam Integer petId) {
        return petService.getAllOwnersByPetid(petId);
    }

    @GetMapping("/info")
    public Pet getPetById(@RequestParam Integer petId) {
        return petService.getPetById(petId);
    }
}
