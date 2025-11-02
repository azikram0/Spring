package com.azikram0.spring.lab2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        //Pet cat = context.getBean("catBean", Pet.class);

        Employee employee = context.getBean("employeeBean", Employee.class);
        employee.callYourPet();

        context.close();
    }
}
