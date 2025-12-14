package com.azikram0.spring.lab456.restcontroller;

import com.azikram0.spring.lab456.dto.MedicalHistoryDto;
import com.azikram0.spring.lab456.entity.MedicalHistory;
import com.azikram0.spring.lab456.service.MedicalHistoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vet/medical-histories")
public class MedicalHistoryRestController {
    private final MedicalHistoryService  medicalHistoryService;

    public MedicalHistoryRestController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping("/add")
    public void addMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto) {
        medicalHistoryService.addMedicalHistory(medicalHistoryDto);
    }

    @DeleteMapping("/delete")
    public void deleteMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto) {
        medicalHistoryService.deleteMedicalHistory(medicalHistoryDto);
    }

    @PutMapping("/update")
    public void updateMedicalHistory(@RequestBody MedicalHistoryDto medicalHistoryDto) {
        medicalHistoryService.updateMedicalHistory(medicalHistoryDto);
    }

    @GetMapping("/info")
    public MedicalHistory getMedicalHistoryById(@RequestParam Integer medicalHistoryId) {
        return medicalHistoryService.getMedicalHistoryById(medicalHistoryId);
    }
}
