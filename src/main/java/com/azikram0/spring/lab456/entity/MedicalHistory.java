package com.azikram0.spring.lab456.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @Column(name = "pet_id")
    private int petId;
    @Column(name = "history_text")
    private String historyText;
    @Column(name = "last_visit_date")
    private LocalDate lastVisitDate;
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    @Column(name = "created_by_specialist")
    private int createdBySpecialist;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(
            name = "created_by_specialist",
            insertable = false,
            updatable = false
    )
    private Specialist specialist;

    public MedicalHistory() {
    }

    public int getCreatedBySpecialist() {
        return createdBySpecialist;
    }

    public void setCreatedBySpecialist(int createdBySpecialist) {
        this.createdBySpecialist = createdBySpecialist;
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
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
