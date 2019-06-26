package com.apdboo.project.controllers;

import com.apdboo.project.requests.TicketRequest;
import com.apdboo.project.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.ticketService.getTickets(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity userTickets() {
        return new ResponseEntity<>(this.ticketService.getTicketsForUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.ticketService.getTicket(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody TicketRequest form) {
        return new ResponseEntity<>(this.ticketService.createTicket(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody TicketRequest form) {
        return new ResponseEntity<>(this.ticketService.updateTicket(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.ticketService.deleteTicket(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
