package com.azikram0.spring.lab5.controller;

import com.azikram0.spring.lab5.dto.PetDto;
import com.azikram0.spring.lab5.form.PetForm;
import com.azikram0.spring.lab5.service.StubDataService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final StubDataService stubDataService;

    public PetController(StubDataService stubDataService) {
        this.stubDataService = stubDataService;
        this.stubDataService.initSampleData();
    }

    // Показать форму добавления питомца
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("petForm", new PetForm());
        model.addAttribute("speciesList", stubDataService.getSpeciesList());
        model.addAttribute("genders", stubDataService.getGenders());
        model.addAttribute("owners", stubDataService.getOwners());
        return "pet_form";
    }

    // Обработка отправки формы
    @PostMapping("/add")
    public String submitAddForm(@Valid @ModelAttribute("petForm") PetForm petForm,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            // При ошибках снова кладём справочные списки
            model.addAttribute("speciesList", stubDataService.getSpeciesList());
            model.addAttribute("genders", stubDataService.getGenders());
            model.addAttribute("owners", stubDataService.getOwners());
            return "pet_form";
        }

        // Добавляем нового питомца в stub-сервис
        PetDto newPet = new PetDto(
                stubDataService.getPets().size() + 1,
                petForm.name(),
                petForm.species(),
                petForm.breed(),
                petForm.birthDate(),
                petForm.gender()
        );
        stubDataService.addPet(newPet);

        return "redirect:/pets/success";
    }

    // Страница успешного добавления
    @GetMapping("/success")
    public String showSuccessPage() {
        return "pet_success";
    }
}

