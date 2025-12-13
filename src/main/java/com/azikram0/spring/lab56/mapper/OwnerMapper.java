package com.azikram0.spring.lab56.mapper;

import com.azikram0.spring.lab4.entity.Owner;
import com.azikram0.spring.lab56.dto.OwnerDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OwnerMapper {
    public Owner map(OwnerDto dto) {
        Owner o = new Owner();
        o.setFirstName(dto.firstName());
        o.setLastName(dto.lastName());
        o.setPhone(dto.phone());
        o.setEmail(dto.email());
        o.setAddress(dto.address());
        o.setCreatedAt(LocalDateTime.now());
        return o;
    }
}
