package com.azikram0.spring.lab3;

import org.springframework.stereotype.Component;

@Component
public interface CanBePutIntoWarehouse {
    default void sendToWarehouse(Warehouse warehouse) throws Exception {
        warehouse.putIntoWarehouse(this);
    }
    String getName();
}
