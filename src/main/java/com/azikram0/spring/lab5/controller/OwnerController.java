package com.azikram0.spring.lab5.controller;
import com.azikram0.spring.lab5.dto.OwnerDto;
import com.azikram0.spring.lab5.form.OwnerForm;
import com.azikram0.spring.lab5.service.StubDataService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final StubDataService stubDataService;

    public OwnerController(StubDataService stubDataService) {
        this.stubDataService = stubDataService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ownerForm", new OwnerForm());
        model.addAttribute("cities", stubDataService.getCities());
        return "owner_form";
    }

    @PostMapping("/add")
    public String submitAddForm(@Valid @ModelAttribute("ownerForm") OwnerForm ownerForm,
                                BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("cities", stubDataService.getCities());
            return "owner_form";
        }

        OwnerDto newOwner = new OwnerDto(
                stubDataService.getOwners().size() + 1,
                ownerForm.firstName(),
                ownerForm.lastName(),
                ownerForm.phone(),
                ownerForm.email(),
                ownerForm.address()
        );
        stubDataService.addOwner(newOwner);
        return "redirect:/owners/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "owner_success";
    }
}

