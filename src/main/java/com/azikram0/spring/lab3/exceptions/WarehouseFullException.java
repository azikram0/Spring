package com.azikram0.spring.lab3.exceptions;

public class WarehouseFullException extends Exception {
    public WarehouseFullException(String message) {
        super(message);
    }
}
