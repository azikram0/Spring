package com.azikram0.spring.lab56.service;

import com.azikram0.spring.lab56.dto.PetDto;
import com.azikram0.spring.lab56.mapper.PetMapper;
import com.azikram0.spring.lab56.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Autowired
    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    public void addPet(PetDto petDto) {
        petRepository.save(petMapper.map(petDto));
    }
}
