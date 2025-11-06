package com.azikram0.spring.lab3;

import com.azikram0.spring.lab3.exceptions.WarehouseFullException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface CanBePutIntoWarehouse {
    default void sendToWarehouse(Warehouse warehouse) throws WarehouseFullException {
        warehouse.putIntoWarehouse(this);
    }
    String getName();
}
