package com.azikram0.spring;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String model = "Generic";

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String drive() {
        return "Driving a " + model;
    }
}


