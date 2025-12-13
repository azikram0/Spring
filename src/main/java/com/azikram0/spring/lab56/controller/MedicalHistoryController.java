package com.azikram0.spring.lab56.controller;

import com.azikram0.spring.lab56.dto.MedicalHistoryDto;
import com.azikram0.spring.lab56.form.MedicalHistoryForm;
import com.azikram0.spring.lab56.service.StubDataService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/histories")
public class MedicalHistoryController {

    private final StubDataService stubDataService;

    public MedicalHistoryController(StubDataService stubDataService) {
        this.stubDataService = stubDataService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("historyForm", new MedicalHistoryForm());
        model.addAttribute("pets", stubDataService.getPets());
        model.addAttribute("specialists", stubDataService.getSpecialists());
        return "history_form";
    }

    @PostMapping("/add")
    public String submitAddForm(@Valid @ModelAttribute("historyForm") MedicalHistoryForm form,
                                BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pets", stubDataService.getPets());
            model.addAttribute("specialists", stubDataService.getSpecialists());
            return "history_form";
        }

        MedicalHistoryDto newHistory = new MedicalHistoryDto(
                form.petId(),
                form.historyText(),
                form.lastVisitDate(),
                LocalDateTime.now(),
                form.specialistId()
        );
        stubDataService.addHistory(newHistory);
        return "redirect:/histories/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "history_success";
    }
}
