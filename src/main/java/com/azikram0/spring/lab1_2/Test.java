package com.azikram0.spring.lab1_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        //Pet pet = context.getBean("myPet", Pet.class);
        //pet.say();

        //Car car = context.getBean("myCar", Car.class);
        //car.start();

        Employee employee = context.getBean("myEmployee", Employee.class);
        employee.callYourPet();
        employee.startYourCar();

        System.out.println(employee.getName());
        System.out.println(employee.getAge());

        context.close();
    }
}
