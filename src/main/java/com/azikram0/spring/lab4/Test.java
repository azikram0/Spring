package com.azikram0.spring.lab4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Test {
    private static final SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        System.out.println("=== Testing relationships between tables ===\n");

        // Тест 1: Создание и сохранение Specialist с Pet (OneToMany)
        testSpecialistPetRelationship();

        // Тест 2: Создание и сохранение Pet с Owner (ManyToMany)
        testPetOwnerRelationship();

        // Тест 3: Создание и сохранение MedicalHistory (OneToOne с Pet, ManyToOne с Specialist)
        testMedicalHistoryRelationships();

        // Тест 4: Проверка каскадного удаления
        testCascadeDelete();

        // Тест 5: Добавление и удаление связей
        testAddRemoveRelationships();

        // Тест 6: Обновление связей
        testUpdateRelationships();

        System.out.println("\n=== All tests completed ===");
        sessionFactory.close();
    }

    /**
     * Тест 1: Проверка связи OneToMany между Specialist и Pet
     */
    private static void testSpecialistPetRelationship() {
        System.out.println("Test 1: Specialist -> Pet (OneToMany)");
        System.out.println("----------------------------------------");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Создаем Specialist
            Specialist specialist = new Specialist();
            specialist.setFirstName("Ivan");
            specialist.setLastName("Petrov");
            specialist.setQualification("Veterinarian");
            specialist.setPhone("+7-999-123-45-67");
            specialist.setEmail("ivan@vet.ru");
            specialist.setCreatedAt(LocalDateTime.now());

            // Создаем Pet
            Pet pet1 = new Pet();
            pet1.setName("Barsik");
            pet1.setSpecies("Cat");
            pet1.setBreed("Persian");
            pet1.setBirthDate(LocalDate.of(2020, 5, 15));
            pet1.setGender("M");
            pet1.setCreatedAt(LocalTime.now());

            Pet pet2 = new Pet();
            pet2.setName("Sharik");
            pet2.setSpecies("Dog");
            pet2.setBreed("Labrador");
            pet2.setBirthDate(LocalDate.of(2019, 3, 20));
            pet2.setGender("M");
            pet2.setCreatedAt(LocalTime.now());

            System.out.println("Creating:");
            System.out.println("  Specialist: " + specialist.getFirstName() + " " + specialist.getLastName());
            System.out.println("  Pet 1: " + pet1.getName() + " (" + pet1.getSpecies() + ")");
            System.out.println("  Pet 2: " + pet2.getName() + " (" + pet2.getSpecies() + ")");

            // Устанавливаем связи
            specialist.addPet(pet1);
            specialist.addPet(pet2);

            // Сохраняем
            session.persist(specialist);
            transaction.commit();

            // Проверяем связь
            session = getSession();
            Specialist savedSpecialist = session.get(Specialist.class, specialist.getId());

            System.out.println("\nSaved to database:");
            System.out.println("  Specialist ID: " + savedSpecialist.getId());
            System.out.println("  Specialist: " + savedSpecialist.getFirstName() + " " + savedSpecialist.getLastName());

            if (savedSpecialist.getPets() != null && !savedSpecialist.getPets().isEmpty()) {
                System.out.println("\nPets linked to this specialist:");
                for (Pet pet : savedSpecialist.getPets()) {
                    System.out.println("  - " + pet.getName() + " (ID: " + pet.getId() + ", " + pet.getSpecies() + ")");
                }
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println();
    }

    /**
     * Тест 2: Проверка связи ManyToMany между Pet и Owner
     */
    private static void testPetOwnerRelationship() {
        System.out.println("Test 2: Pet <-> Owner (ManyToMany)");
        System.out.println("----------------------------------------");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Создаем Owner
            Owner owner1 = new Owner();
            owner1.setFirstName("Maria");
            owner1.setLastName("Sidorova");
            owner1.setPhone("+7-999-111-22-33");
            owner1.setEmail("maria@mail.ru");
            owner1.setAddress("Moscow, Lenina St., 1");
            owner1.setCreatedAt(LocalDateTime.now());

            Owner owner2 = new Owner();
            owner2.setFirstName("Alexey");
            owner2.setLastName("Ivanov");
            owner2.setPhone("+7-999-444-55-66");
            owner2.setEmail("alex@mail.ru");
            owner2.setAddress("St. Petersburg, Nevsky Ave., 10");
            owner2.setCreatedAt(LocalDateTime.now());

            // Создаем Pet
            Pet pet = new Pet();
            pet.setName("Murka");
            pet.setSpecies("Cat");
            pet.setBreed("British");
            pet.setBirthDate(LocalDate.of(2021, 7, 10));
            pet.setGender("F");
            pet.setCreatedAt(LocalTime.now());

            System.out.println("Creating:");
            System.out.println("  Pet: " + pet.getName() + " (" + pet.getSpecies() + ")");
            System.out.println("  Owner 1: " + owner1.getFirstName() + " " + owner1.getLastName());
            System.out.println("  Owner 2: " + owner2.getFirstName() + " " + owner2.getLastName());

            // Сохраняем
            pet.addOwner(owner1);
            pet.addOwner(owner2);
            session.persist(pet);
            transaction.commit();

            // Проверяем связь
            session = getSession();
            Pet savedPet = session.get(Pet.class, pet.getId());

            System.out.println("\nSaved to database:");
            System.out.println("  Pet ID: " + savedPet.getId() + " - " + savedPet.getName());

            if (savedPet.getOwners() != null && !savedPet.getOwners().isEmpty()) {
                System.out.println("\nOwners linked to this pet:");
                for (Owner owner : savedPet.getOwners()) {
                    System.out.println("  - " + owner.getFirstName() + " " + owner.getLastName() + " (ID: " + owner.getId() + ")");
                }
            }

            // Проверяем обратную связь
            Owner savedOwner = session.get(Owner.class, owner1.getId());
            System.out.println("\nPets linked to owner " + savedOwner.getFirstName() + " " + savedOwner.getLastName() + ":");
            if (savedOwner.getPets() != null && !savedOwner.getPets().isEmpty()) {
                for (Pet p : savedOwner.getPets()) {
                    System.out.println("  - " + p.getName() + " (ID: " + p.getId() + ")");
                }
            } else {
                System.out.println("  (none)");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println();
    }

    /**
     * Тест 3: Проверка связей MedicalHistory (OneToOne с Pet, ManyToOne с Specialist)
     */
    private static void testMedicalHistoryRelationships() {
        System.out.println("Test 3: MedicalHistory (OneToOne with Pet, ManyToOne with Specialist)");
        System.out.println("----------------------------------------");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Создаем Specialist
            Specialist specialist = new Specialist();
            specialist.setFirstName("Anna");
            specialist.setLastName("Kozlova");
            specialist.setQualification("Surgeon");
            specialist.setPhone("+7-999-777-88-99");
            specialist.setEmail("anna@vet.ru");
            specialist.setCreatedAt(LocalDateTime.now());

            // Создаем Pet
            Pet pet = new Pet();
            pet.setName("Rex");
            pet.setSpecies("Dog");
            pet.setBreed("Shepherd");
            pet.setBirthDate(LocalDate.of(2018, 1, 5));
            pet.setGender("M");
            pet.setCreatedAt(LocalTime.now());

            System.out.println("Creating:");
            System.out.println("  Specialist: " + specialist.getFirstName() + " " + specialist.getLastName());
            System.out.println("  Pet: " + pet.getName() + " (" + pet.getSpecies() + ")");

            specialist.addPet(pet);
            session.persist(specialist);
            transaction.commit();

            // Создаем MedicalHistory
            session = getSession();
            transaction = session.beginTransaction();

            pet = session.get(Pet.class, pet.getId());
            specialist = session.get(Specialist.class, specialist.getId());

            MedicalHistory history = new MedicalHistory();
            history.setHistoryText("Routine checkup. Condition satisfactory.");
            history.setLastVisitDate(LocalDate.now());
            history.setLastUpdated(LocalTime.now());
            history.setPet(pet);
            history.setSpecialist(specialist);

            session.persist(history);
            transaction.commit();

            System.out.println("\nMedical History created:");
            System.out.println("  History text: " + history.getHistoryText());
            System.out.println("  Last visit: " + history.getLastVisitDate());

            // Проверяем связи
            session = getSession();
            MedicalHistory savedHistory = session.get(MedicalHistory.class, pet.getId());

            System.out.println("\nLoaded from database:");
            System.out.println("  Pet ID: " + savedHistory.getPetId());
            if (savedHistory.getPet() != null) {
                System.out.println("  Pet: " + savedHistory.getPet().getName());
            }
            if (savedHistory.getSpecialist() != null) {
                System.out.println("  Specialist: " + savedHistory.getSpecialist().getFirstName() +
                        " " + savedHistory.getSpecialist().getLastName());
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println();
    }

    /**
     * Тест 4: Проверка каскадного удаления
     */
    private static void testCascadeDelete() {
        System.out.println("Test 4: Cascade delete");
        System.out.println("----------------------------------------");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Создаем данные для удаления
            Specialist specialist = new Specialist();
            specialist.setFirstName("Test");
            specialist.setLastName("Delete");
            specialist.setQualification("Test");
            specialist.setPhone("+7-999-000-00-00");
            specialist.setEmail("test@test.ru");
            specialist.setCreatedAt(LocalDateTime.now());

            Pet pet = new Pet();
            pet.setName("TestPet");
            pet.setSpecies("Test");
            pet.setBreed("Test");
            pet.setBirthDate(LocalDate.now());
            pet.setGender("M");
            pet.setCreatedAt(LocalTime.now());

            System.out.println("Creating:");
            System.out.println("  Specialist: " + specialist.getFirstName() + " " + specialist.getLastName());
            System.out.println("  Pet: " + pet.getName());

            specialist.addPet(pet);
            session.persist(specialist);
            transaction.commit();

            int specialistId = specialist.getId();
            int petId = pet.getId();

            System.out.println("\nSaved to database:");
            System.out.println("  Specialist ID: " + specialistId);
            System.out.println("  Pet ID: " + petId);

            // Удаляем Specialist (должен каскадно удалить Pet)
            session = getSession();
            transaction = session.beginTransaction();
            Specialist toDelete = session.get(Specialist.class, specialistId);
            if (toDelete != null) {
                System.out.println("\nDeleting Specialist ID: " + specialistId);
                session.remove(toDelete);
                transaction.commit();

                // Проверяем, что Pet тоже удален
                session = getSession();
                Pet deletedPet = session.get(Pet.class, petId);
                if (deletedPet == null) {
                    System.out.println("Result: Pet ID " + petId + " was also deleted (cascade works!)");
                } else {
                    System.out.println("Result: Pet ID " + petId + " still exists (cascade doesn't work)");
                }
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println();
    }

    /**
     * Тест 5: Добавление и удаление связей
     */
    private static void testAddRemoveRelationships() {
        System.out.println("Test 5: Adding and removing relationships");
        System.out.println("----------------------------------------");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Создаем Owner
            Owner owner = new Owner();
            owner.setFirstName("Dmitry");
            owner.setLastName("Smirnov");
            owner.setPhone("+7-999-222-33-44");
            owner.setEmail("dmitry@mail.ru");
            owner.setAddress("Kazan, Baumana St., 5");
            owner.setCreatedAt(LocalDateTime.now());

            // Создаем Pet
            Pet pet = new Pet();
            pet.setName("Tuzik");
            pet.setSpecies("Dog");
            pet.setBreed("Mixed");
            pet.setBirthDate(LocalDate.of(2022, 4, 12));
            pet.setGender("M");
            pet.setCreatedAt(LocalTime.now());

            session.persist(owner);
            session.persist(pet);
            transaction.commit();

            System.out.println("Created:");
            System.out.println("  Owner: " + owner.getFirstName() + " " + owner.getLastName() + " (ID: " + owner.getId() + ")");
            System.out.println("  Pet: " + pet.getName() + " (ID: " + pet.getId() + ")");

            // Добавляем связь
            session = getSession();
            transaction = session.beginTransaction();
            Pet petToUpdate = session.get(Pet.class, pet.getId());
            Owner ownerToAdd = session.get(Owner.class, owner.getId());

            System.out.println("\nBEFORE adding relationship:");
            System.out.println("  Pet " + petToUpdate.getName() + " has owners: " +
                    (petToUpdate.getOwners() != null ? petToUpdate.getOwners().size() : 0));

            petToUpdate.addOwner(ownerToAdd);
            session.merge(petToUpdate);
            transaction.commit();

            // Проверяем
            session = getSession();
            Pet savedPet = session.get(Pet.class, pet.getId());
            System.out.println("\nAFTER adding relationship:");
            if (savedPet.getOwners() != null && !savedPet.getOwners().isEmpty()) {
                System.out.println("  Pet " + savedPet.getName() + " now has " + savedPet.getOwners().size() + " owner(s):");
                for (Owner o : savedPet.getOwners()) {
                    System.out.println("    - " + o.getFirstName() + " " + o.getLastName() + " (ID: " + o.getId() + ")");
                }
            } else {
                System.out.println("  Pet " + savedPet.getName() + " has no owners");
            }

            // Удаляем связь
            session = getSession();
            transaction = session.beginTransaction();
            Pet petToRemove = session.get(Pet.class, pet.getId());
            Owner ownerToRemove = session.get(Owner.class, owner.getId());

            System.out.println("\nBEFORE removing relationship:");
            System.out.println("  Pet " + petToRemove.getName() + " has owners: " +
                    (petToRemove.getOwners() != null ? petToRemove.getOwners().size() : 0));

            petToRemove.removeOwner(ownerToRemove);
            session.merge(petToRemove);
            transaction.commit();

            // Проверяем
            session = getSession();
            Pet updatedPet = session.get(Pet.class, pet.getId());
            System.out.println("\nAFTER removing relationship:");
            if (updatedPet.getOwners() != null && !updatedPet.getOwners().isEmpty()) {
                System.out.println("  Pet " + updatedPet.getName() + " still has " + updatedPet.getOwners().size() + " owner(s):");
                for (Owner o : updatedPet.getOwners()) {
                    System.out.println("    - " + o.getFirstName() + " " + o.getLastName());
                }
            } else {
                System.out.println("  Pet " + updatedPet.getName() + " now has no owners (relationship removed)");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println();
    }

    /**
     * Тест 6: Обновление связей
     */
    private static void testUpdateRelationships() {
        System.out.println("Test 6: Updating relationships");
        System.out.println("----------------------------------------");

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Создаем двух специалистов
            Specialist specialist1 = new Specialist();
            specialist1.setFirstName("First");
            specialist1.setLastName("Specialist");
            specialist1.setQualification("Therapist");
            specialist1.setPhone("+7-999-111-11-11");
            specialist1.setEmail("first@vet.ru");
            specialist1.setCreatedAt(LocalDateTime.now());

            Specialist specialist2 = new Specialist();
            specialist2.setFirstName("Second");
            specialist2.setLastName("Specialist");
            specialist2.setQualification("Surgeon");
            specialist2.setPhone("+7-999-222-22-22");
            specialist2.setEmail("second@vet.ru");
            specialist2.setCreatedAt(LocalDateTime.now());

            // Создаем Pet
            Pet pet = new Pet();
            pet.setName("Bobik");
            pet.setSpecies("Dog");
            pet.setBreed("Dachshund");
            pet.setBirthDate(LocalDate.of(2020, 6, 1));
            pet.setGender("M");
            pet.setCreatedAt(LocalTime.now());

            specialist1.addPet(pet);
            session.persist(specialist1);
            session.persist(specialist2);

            System.out.println("Creating:");
            System.out.println("  Specialist 1: " + specialist1.getFirstName() + " " + specialist1.getLastName() + " (ID: " + specialist1.getId() + ")");
            System.out.println("  Specialist 2: " + specialist2.getFirstName() + " " + specialist2.getLastName() + " (ID: " + specialist2.getId() + ")");
            System.out.println("  Pet: " + pet.getName());

            transaction.commit();

            System.out.println("\nBEFORE update:");
            System.out.println("  Pet " + pet.getName() + " is linked to Specialist ID: " + pet.getSpecialistId());

            // Обновляем связь - меняем специалиста
            session = getSession();
            transaction = session.beginTransaction();
            Pet petToUpdate = session.get(Pet.class, pet.getId());
            Specialist newSpecialist = session.get(Specialist.class, specialist2.getId());

            System.out.println("\nUpdating: changing specialist from ID " + petToUpdate.getSpecialistId() +
                    " to ID " + newSpecialist.getId());

            petToUpdate.setSpecialist(newSpecialist);
            session.merge(petToUpdate);
            transaction.commit();

            // Проверяем
            session = getSession();
            Pet updatedPet = session.get(Pet.class, pet.getId());
            System.out.println("\nAFTER update:");
            System.out.println("  Pet " + updatedPet.getName() + " is now linked to Specialist ID: " + updatedPet.getSpecialistId());
            if (updatedPet.getSpecialist() != null) {
                System.out.println("  Specialist: " + updatedPet.getSpecialist().getFirstName() +
                        " " + updatedPet.getSpecialist().getLastName());
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println();
    }
}
