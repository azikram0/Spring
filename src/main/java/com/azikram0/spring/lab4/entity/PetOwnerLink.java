package com.azikram0.spring.lab4.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pet_owner_link")
public class PetOwnerLink {
    @EmbeddedId
    private PetOwnerLinkId id;
    @Column(name = "is_primary_contact")
    private boolean isPrimaryContact;
    @Column(name = "contact_note")
    private String contactNote;
    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public PetOwnerLink() {
    }

    public PetOwnerLinkId getId() {
        return id;
    }

    public void setId(PetOwnerLinkId id) {
        this.id = id;
    }

    public boolean isPrimaryContact() {
        return isPrimaryContact;
    }

    public void setPrimaryContact(boolean primaryContact) {
        isPrimaryContact = primaryContact;
    }

    public String getContactNote() {
        return contactNote;
    }

    public void setContactNote(String contactNote) {
        this.contactNote = contactNote;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Embeddable
    public static class PetOwnerLinkId implements Serializable {
        @Column(name = "pet_id")
        private int petId;
        @Column(name = "owner_id")
        private int ownerId;

        public PetOwnerLinkId() {
        }

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
            if (!(o instanceof PetOwnerLinkId that)) return false;
            return petId == that.petId && ownerId == that.ownerId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(petId, ownerId);
        }
    }
}
