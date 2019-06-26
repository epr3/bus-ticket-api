package com.apdboo.project.exceptions;

public class RouteNotFoundException extends RuntimeException{
    public RouteNotFoundException() {
    }

    public RouteNotFoundException(Long id) {
        super("Route: " + id + " not found.");
    }
}
