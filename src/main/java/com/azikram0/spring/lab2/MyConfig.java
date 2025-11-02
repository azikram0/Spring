package com.azikram0.spring.lab2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.azikram0.spring.lab2")
public class MyConfig {
    @Bean
    public Pet catBean(){
        return new Cat();
    }

    @Bean
    public Pet dogBean(){
        return new Dog();
    }

    @Bean
    public Car carBean(){
        return new Car();
    }

    @Bean
    public Employee employeeBean(){
        return new Employee(catBean(), carBean());
    }
}
