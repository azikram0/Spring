package com.azikram0.spring.lab2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class Employee {
    private Pet pet;
    private Car car;
    @Value ("${employee.name}")
    private String name;
    @Value ("${employee.age}")
    private int age;

    //@Autowired
    public Employee(/*@Qualifier("dog")*/ Pet pet, Car car) {
        this.pet = pet;
        this.car = car;
        System.out.println("Employee bean is created");
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

    @PostConstruct
    private void init(){
        System.out.println("Class Employee: init method");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("Class Employee: destroy method");
    }
}


