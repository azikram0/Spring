package com.azikram0.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isBlank()) {
            return "Hello, world!";
        }
        return "Hello, " + name;
    }

    @GetMapping("/about")
    public String about() {
        return "about us";
    }

    @GetMapping("/options")
    public String options(@RequestParam(value = "value", required = false) String value) {
        if (value == null || value.isBlank()) {
            return "options";
        }
        return "not an option";
    }
}