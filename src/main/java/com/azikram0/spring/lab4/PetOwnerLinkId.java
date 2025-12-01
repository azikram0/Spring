package com.azikram0.spring.lab4;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PetOwnerLinkId implements Serializable {
    @Column(name = "pet_id")
    private int petId;
    @Column(name = "owner_id")
    private int ownerId;

    public PetOwnerLinkId() {}

    public PetOwnerLinkId(int petId, int ownerId) {
        this.petId = petId;
        this.ownerId = ownerId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetOwnerLinkId)) return false;
        PetOwnerLinkId that = (PetOwnerLinkId) o;
        return petId == that.petId && ownerId == that.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, ownerId);
    }
}
