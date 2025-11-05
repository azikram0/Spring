package com.azikram0.spring.lab3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("singleton")
public class Warehouse {
    private final List<String> things = new ArrayList<>();

    public void putIntoWarehouse(CanBePutIntoWarehouse thing) throws Exception {
        if (things.size() == 2) {
            throw new Exception("cклад переполнен");
        } else {
            things.add(thing.getName());
        }
    }

    public void takeFromWarehouse(CanBePutIntoWarehouse thing, int n) throws Exception {
        long count = Collections.frequency(things, thing.getName());
        if (count == 0) {
            throw new Exception("на складе нет такого предмета");
        }
        if (n > count) {
            throw new Exception("на складе нет столько предметов");
        }
        things.remove(thing.getName());
    }

    public List<String> getWarehouse() {
        return List.copyOf(things);
    }
}
