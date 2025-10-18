package com.azikram0.spring;

import org.springframework.stereotype.Component;

@Component
public class Pet {
    private String name = "Buddy";
    private String type = "dog";

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String speak() {
        return ("dog".equalsIgnoreCase(type) ? "Woof!" : "Meow!");
    }
}


