package com.azikram0.spring.lab456.restcontroller;

import com.azikram0.spring.lab456.dto.OwnerDto;
import com.azikram0.spring.lab456.dto.PetDto;
import com.azikram0.spring.lab456.dto.PetOwnerLinkDto;
import com.azikram0.spring.lab456.entity.Owner;
import com.azikram0.spring.lab456.repository.PetOwnerLinkRepository;
import com.azikram0.spring.lab456.service.OwnerService;
import com.azikram0.spring.lab456.service.PetOwnerLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vet/owners")
public class OwnerRestController {
    private final OwnerService ownerService;
    private final PetOwnerLinkService petOwnerLinkService;

    public OwnerRestController(OwnerService ownerService,
                               PetOwnerLinkService petOwnerLinkService) {
        this.ownerService = ownerService;
        this.petOwnerLinkService = petOwnerLinkService;
    }

    @PostMapping("/add")
    public void addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.addOwner(ownerDto);
    }

    @DeleteMapping("/delete")
    public void deleteOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.deleteOwner(ownerDto);
    }

    @PutMapping("/update")
    public void updateOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.updateOwner(ownerDto);
    }

    @PostMapping("/pets/add")
    public void addLink(@RequestBody PetOwnerLinkDto petOwnerLinkDto) {
        petOwnerLinkService.addLink(petOwnerLinkDto);
    }

    @DeleteMapping("/pets/delete")
    public void deleteLink(@RequestBody PetOwnerLinkDto petOwnerLinkDto) {
        petOwnerLinkService.deleteLink(petOwnerLinkDto);
    }

    @PutMapping("/pets/update")
    public void updateLink(
            @RequestParam Integer petId,
            @RequestParam Integer ownerId,
            @RequestBody PetOwnerLinkDto petOwnerLinkDto) {
        petOwnerLinkService.updateLink(petOwnerLinkDto,  petId, ownerId);
    }

    @GetMapping("/pets/get")
    public List<String> getAllPetsByOwnerId(@RequestParam Integer ownerId) {
        return ownerService.getAllPetsByOwnerId(ownerId);
    }

    @GetMapping("info")
    public Owner getOwnerById(@RequestParam Integer ownerId) {
        return ownerService.getOwnerById(ownerId);
    }
}
