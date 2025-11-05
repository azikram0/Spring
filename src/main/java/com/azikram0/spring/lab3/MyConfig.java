package com.azikram0.spring.lab3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.azikram0.spring.lab3")
@EnableAspectJAutoProxy
public class MyConfig {
}
