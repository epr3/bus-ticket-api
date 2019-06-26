package com.apdboo.project.exceptions;

public class BusNotFoundException extends RuntimeException {
    public BusNotFoundException() {
    }

    public BusNotFoundException(Long id) {
        super("Bus: " + id + " not found.");
    }
}
