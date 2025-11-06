package com.azikram0.spring.lab3.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
