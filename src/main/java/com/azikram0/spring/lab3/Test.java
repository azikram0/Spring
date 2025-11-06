package com.azikram0.spring.lab3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class)) {

            Warehouse warehouse = context.getBean("warehouse", Warehouse.class);

            CanBePutIntoWarehouse barrel = context.getBean("barrel", CanBePutIntoWarehouse.class);
            barrel.sendToWarehouse(warehouse);

            CanBePutIntoWarehouse bucket1 = context.getBean("bucket", CanBePutIntoWarehouse.class);
            bucket1.sendToWarehouse(warehouse);

            CanBePutIntoWarehouse bucket2 = context.getBean("bucket", CanBePutIntoWarehouse.class);
            bucket2.sendToWarehouse(warehouse);

            CanBePutIntoWarehouse box = context.getBean("box", CanBePutIntoWarehouse.class);

            warehouse.takeFromWarehouse(bucket1, 3);

            warehouse.takeFromWarehouse(bucket1, 2);

            box.sendToWarehouse(warehouse);

        } catch (Exception exception) {

        }
    }
}
