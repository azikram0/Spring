package com.azikram0.spring.lab3;

import org.springframework.stereotype.Component;

@Component
public class Box implements  CanBePutIntoWarehouse {
    @Override
    public String getName() {
        return "box";
    }
}
