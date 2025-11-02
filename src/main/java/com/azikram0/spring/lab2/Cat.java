package com.azikram0.spring.lab2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component
public class Cat implements Pet {
    public Cat() {
        System.out.println("Cat bean is created");
    }

    @Override
    public void say(){
        System.out.println("Meow");
    }

    @PostConstruct
    private void init(){
        System.out.println("Class Cat: init method");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("Class Cat: destroy method");
    }
}
