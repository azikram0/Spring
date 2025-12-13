package com.azikram0.spring.lab56.controller;

import com.azikram0.spring.lab56.dto.SpecialistDto;
import com.azikram0.spring.lab56.form.SpecialistForm;
import com.azikram0.spring.lab56.service.StubDataService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/specialists")
public class SpecialistController {

    private final StubDataService stubDataService;

    public SpecialistController(StubDataService stubDataService) {
        this.stubDataService = stubDataService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("specialistForm", new SpecialistForm());
        model.addAttribute("specializations", stubDataService.getSpecializations());
        return "specialist_form";
    }

    @PostMapping("/add")
    public String submitAddForm(@Valid @ModelAttribute("specialistForm") SpecialistForm form,
                                BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("specializations", stubDataService.getSpecializations());
            return "specialist_form";
        }

        SpecialistDto newSpecialist = new SpecialistDto(
                stubDataService.getSpecialists().size() + 1,
                form.firstName(),
                form.lastName(),
                form.qualification(),
                form.phone(),
                form.email()
        );
        stubDataService.addSpecialist(newSpecialist);
        return "redirect:/specialists/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "specialist_success";
    }
}
