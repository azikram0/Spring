package com.azikram0.spring.lab3;

import com.azikram0.spring.lab3.exceptions.ItemNotFoundException;
import com.azikram0.spring.lab3.exceptions.WarehouseFullException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class Warehouse {
    private List<CanBePutIntoWarehouse> things = new ArrayList<>();

    public void putIntoWarehouse(CanBePutIntoWarehouse thing) throws WarehouseFullException {
        if (things.size() == 3) {
            throw new WarehouseFullException("cклад переполнен");
        } else {
            things.add(thing);
        }
    }

    public void takeFromWarehouse(CanBePutIntoWarehouse thing, int n) throws ItemNotFoundException {
        long count = things
                .stream()
                .filter(it -> it.getName().equals(thing.getName()))
                .toList().size();
        if (count == 0) {
            throw new ItemNotFoundException("на складе нет такого предмета");
        }
        if (n > count) {
            throw new ItemNotFoundException("на складе нет столько предметов");
        }
        for (int i = 0; i < n; i++) {
            for (CanBePutIntoWarehouse elem : things) {
                if (elem.getName().equals(thing.getName())) {
                    things.remove(elem);
                    break;
                }
            }
        }
    }

    public List<CanBePutIntoWarehouse> getThings() {
        return things;
    }
}
