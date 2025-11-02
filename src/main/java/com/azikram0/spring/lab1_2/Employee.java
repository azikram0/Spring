package com.azikram0.spring.lab1_2;

public class Employee {
    private Pet pet;
    private Car car;
    private String name;
    private int age;

    public Employee(Pet pet, Car car) {
        this.pet = pet;
        this.car = car;
    }

    public void callYourPet(){
        System.out.println("Hello, my lovely Pet!");
        pet.say();
    }

    public void startYourCar(){
        System.out.println("I start the car");
        car.start();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        System.out.println("Class Employee: set name");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("Class Employee: set age");
        this.age = age;
    }
}


