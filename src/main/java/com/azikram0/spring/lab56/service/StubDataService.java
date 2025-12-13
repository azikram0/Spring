package com.azikram0.spring.lab56.service;

import com.azikram0.spring.lab56.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StubDataService {
    private final List<String> speciesList = List.of("Dog", "Cat", "Bird");
    private final List<String> genders = List.of("M", "F");
    private final List<String> specializations = List.of("SURGERY", "DERMATOLOGY", "DENTISTRY");
    private final List<String> cities = List.of("Moscow", "Samara", "Ivanovo");

    private final List<PetDto> pets = new ArrayList<>();
    private final List<OwnerDto> owners = new ArrayList<>();
    private final List<SpecialistDto> specialists = new ArrayList<>();
    private final List<MedicalHistoryDto> histories = new ArrayList<>();
    private final List<PetOwnerLinkDto> petOwnerLinks = new ArrayList<>();

    public List<String> getSpeciesList() { return speciesList; }
    public List<String> getGenders() { return genders; }
    public List<String> getSpecializations() { return specializations; }
    public List<String> getCities() { return cities; }

    public List<PetDto> getPets() { return pets; }
    public List<OwnerDto> getOwners() { return owners; }
    public List<SpecialistDto> getSpecialists() { return specialists; }
    public List<MedicalHistoryDto> getHistories() { return histories; }
    public List<PetOwnerLinkDto> getPetOwnerLinks() { return petOwnerLinks; }

    public void addPet(PetDto pet) { pets.add(pet); }
    public void addOwner(OwnerDto owner) { owners.add(owner); }
    public void addSpecialist(SpecialistDto specialist) { specialists.add(specialist); }
    public void addHistory(MedicalHistoryDto history) { histories.add(history); }
    public void addPetOwnerLink(PetOwnerLinkDto link) { petOwnerLinks.add(link); }

    public Optional<PetDto> findPetById(int id) {
        return pets.stream().filter(p -> p.id() == id).findFirst();
    }

    public Optional<OwnerDto> findOwnerById(int id) {
        return owners.stream().filter(o -> o.id() == id).findFirst();
    }

    public Optional<SpecialistDto> findSpecialistById(int id) {
        return specialists.stream().filter(s -> s.id().equals(id)).findFirst();
    }

    // Примеры заглушек по умолчанию
    public void initSampleData() {
        owners.add(new OwnerDto(1, "John", "Smith", "+11234567890", "john.smith@mail.com", "10 Baker Street"));
        owners.add(new OwnerDto(2, "Mary", "Johnson", "+10987654321", "mary.johnson@mail.com", "5 Elm Avenue"));

        pets.add(new PetDto(1, "Buddy", "Dog", "Mixed Breed", LocalDate.of(2020, 5, 20), "MALE"));
        pets.add(new PetDto(2, "Misty", "Cat", "Siamese", LocalDate.of(2019, 8, 15), "FEMALE"));

        specialists.add(new SpecialistDto(1, "Alex", "Brown", "Veterinary Surgeon", "+11001112233", "alex.brown@mail.com"));
        specialists.add(new SpecialistDto(2, "Olivia", "Davis", "Veterinary Dermatologist", "+11002223344", "olivia.davis@mail.com"));

        histories.add(new MedicalHistoryDto(1, "Rabies Vaccination", LocalDate.of(2023, 1, 10),
                LocalDateTime.now(), 1));
    }

}

