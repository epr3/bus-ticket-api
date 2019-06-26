package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.*;
import com.apdboo.project.models.*;
import com.apdboo.project.repositories.BusRepository;
import com.apdboo.project.repositories.IntervalRepository;
import com.apdboo.project.repositories.RouteRepository;
import com.apdboo.project.repositories.TicketRepository;
import com.apdboo.project.requests.CityRequest;
import com.apdboo.project.requests.TicketRequest;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private IntervalRepository intervalRepository;

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("")
    public ResponseEntity all() {
        List<Ticket> ticketList = this.ticketRepository.findAll();
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity userTickets() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Ticket> ticketList = this.ticketRepository.getTicketsByPassengerId(currentUser.getPassenger().getId());
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(CityNotFoundException::new);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody TicketRequest form) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bus bus = this.busRepository.findById(form.getBus_id()).orElseThrow(BusNotFoundException::new);
        Route route = this.routeRepository.findById(form.getRoute_id()).orElseThrow(RouteNotFoundException::new);
        Interval interval = this.intervalRepository.findById(form.getInterval_id()).orElseThrow(IntervalNotFoundException::new);
        String shortId = RandomStringUtils.random(8, true, true);
        Float total = route.getPrice();
        for(Amenity a : bus.getAmenities()) {
            if (a.getPriceModifierType().equals("multiply")) {
                total *= a.getPriceModifier();
            }
            if (a.getPriceModifierType().equals("add")) {
                total += a.getPriceModifier();
            }
        }

        Ticket ticket = this.ticketRepository.save(
                Ticket.builder()
                        .ticket_date(form.getTicket_date())
                        .code(shortId)
                        .total(total)
                        .bus(bus)
                        .route(route)
                        .interval(interval)
                        .passenger(currentUser.getPassenger())
                        .build()
        );
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody TicketRequest form) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        Bus bus = this.busRepository.findById(form.getBus_id()).orElseThrow(BusNotFoundException::new);
        Route route = this.routeRepository.findById(form.getRoute_id()).orElseThrow(RouteNotFoundException::new);
        Interval interval = this.intervalRepository.findById(form.getInterval_id()).orElseThrow(IntervalNotFoundException::new);
        ticket.setBus(bus);
        ticket.setRoute(route);
        ticket.setInterval(interval);
        ticket.setTicket_date(form.getTicket_date());

        Float total = route.getPrice();
        for(Amenity a : bus.getAmenities()) {
            if (a.getPriceModifierType().equals("multiply")) {
                total *= a.getPriceModifier();
            }
            if (a.getPriceModifierType().equals("add")) {
                total += a.getPriceModifier();
            }
        }

        ticket.setTotal(total);

        this.ticketRepository.save(ticket);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(CityNotFoundException::new);
        this.ticketRepository.delete(ticket);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
