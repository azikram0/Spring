package com.azikram0.spring.lab56.restcontroller;

import com.azikram0.spring.lab56.dto.OwnerDto;
import com.azikram0.spring.lab56.service.OwnerService;
import com.azikram0.spring.lab56.service.VetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerRestController {
    private final OwnerService ownerService;

    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping("/add")
    public void addOwner(OwnerDto ownerDto) {
        ownerService.addOwner(ownerDto);
    }

}
