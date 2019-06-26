package com.apdboo.project.exceptions;

public class ItineraryNotFoundException extends RuntimeException{
    public ItineraryNotFoundException() {
    }

    public ItineraryNotFoundException(Long id) {
        super("Itinerary: " + id + " not found.");
    }
}
