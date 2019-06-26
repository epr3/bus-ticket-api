package com.apdboo.project.exceptions;

public class IntervalNotFoundException extends RuntimeException{
    public IntervalNotFoundException() {
    }

    public IntervalNotFoundException(Long id) {
        super("Interval: " + id + " not found.");
    }
}
