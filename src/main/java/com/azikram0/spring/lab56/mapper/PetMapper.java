package com.azikram0.spring.lab56.mapper;

import com.azikram0.spring.lab4.entity.Pet;
import com.azikram0.spring.lab56.dto.PetDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PetMapper {
    public Pet map(PetDto petDto) {
        Pet pet = new Pet();
        pet.setName(petDto.name());
        pet.setBirthDate(petDto.birthDate());
        pet.setGender(petDto.gender());
        pet.setBreed(petDto.breed());
        pet.setSpeciesId(petDto.speciesId());
        pet.setCreatedAt(LocalDateTime.now());
        return pet;
    }
}
