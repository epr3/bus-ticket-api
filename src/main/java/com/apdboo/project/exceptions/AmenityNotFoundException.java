package com.apdboo.project.exceptions;

public class AmenityNotFoundException extends RuntimeException {
    public AmenityNotFoundException() {
    }

    public AmenityNotFoundException(Long id) {
        super("Amenity: " + id + " not found.");
    }
}
