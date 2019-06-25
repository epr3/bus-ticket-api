package com.apdboo.project.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
    }

    public CityNotFoundException(Long id) {
        super("City: " + id + " not found.");
    }
}