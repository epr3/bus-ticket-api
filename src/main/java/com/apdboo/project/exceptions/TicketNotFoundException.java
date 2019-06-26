package com.apdboo.project.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException() {
    }

    public TicketNotFoundException(Long id) {
        super("Ticket: " + id + " not found.");
    }
}
