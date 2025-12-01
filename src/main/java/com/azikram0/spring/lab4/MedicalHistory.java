package com.azikram0.spring.lab4;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @Column(name = "pet_id")
    private Integer petId;
    @Column(name = "history_text")
    private String historyText;
    @Column(name = "last_visit_date")
    private LocalDate lastVisitDate;
    @Column(name = "last_updated")
    private LocalTime lastUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by_specialist")
    private Specialist specialist;

    public MedicalHistory() {
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getHistoryText() {
        return historyText;
    }

    public void setHistoryText(String historyText) {
        this.historyText = historyText;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public LocalTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "petId=" + petId +
                ", historyText='" + historyText + '\'' +
                ", lastVisitDate=" + lastVisitDate +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
