package com.azikram0.spring.lab2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component
public class Dog implements Pet {
    public Dog() {
        System.out.println("Dog bean is created");
    }

    @Override
    public void say(){
        System.out.println("Woof");
    }

    @PostConstruct
    private void init(){
        System.out.println("Class Dog: init method");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("Class Dog: destroy method");
    }
}
