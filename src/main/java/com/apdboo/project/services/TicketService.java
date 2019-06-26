package com.apdboo.project.services;

import com.apdboo.project.models.Ticket;
import com.apdboo.project.requests.TicketRequest;

import java.util.List;

public interface TicketService {
    Ticket createTicket(TicketRequest ticketRequest);
    Ticket updateTicket(Long id, TicketRequest ticketRequest);
    void deleteTicket(Long id);
    Ticket getTicket(Long id);
    List<Ticket> getTickets();
    List<Ticket> getTicketsForUser();
}
