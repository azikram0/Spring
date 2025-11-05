package com.azikram0.spring.lab3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class)) {

            Warehouse warehouse = context.getBean("warehouse", Warehouse.class);

            CanBePutIntoWarehouse barrel = context.getBean("barrel", CanBePutIntoWarehouse.class);
            barrel.sendToWarehouse(warehouse);

            CanBePutIntoWarehouse bucket = context.getBean("bucket", CanBePutIntoWarehouse.class);
            bucket.sendToWarehouse(warehouse);

            CanBePutIntoWarehouse box = context.getBean("box", CanBePutIntoWarehouse.class);

            warehouse.takeFromWarehouse(box, 1);

        } catch (Exception exception) {}
    }
}
