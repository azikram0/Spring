package com.azikram0.spring.lab56.service;

import com.azikram0.spring.lab56.dto.OwnerDto;
import com.azikram0.spring.lab56.mapper.OwnerMapper;
import com.azikram0.spring.lab56.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerService(OwnerMapper ownerMapper, OwnerRepository ownerRepository) {
        this.ownerMapper = ownerMapper;
        this.ownerRepository = ownerRepository;
    }

    public void addOwner(OwnerDto ownerDto) {
        ownerRepository.save(ownerMapper.map(ownerDto));
    }
}
