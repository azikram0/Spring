package com.azikram0.spring.lab2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component
public class Car {
    public Car() {
        System.out.println("Car bean is created");
    }

    public void start(){
        System.out.println("Car is running");
    }

    @PostConstruct
    private void init(){
        System.out.println("Class Car: init method");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("Class Car: destroy method");
    }
}


