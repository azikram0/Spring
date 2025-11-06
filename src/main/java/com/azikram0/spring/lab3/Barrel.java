package com.azikram0.spring.lab3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Barrel implements  CanBePutIntoWarehouse {
    @Override
    public String getName() {
        return "barrel";
    }
}
