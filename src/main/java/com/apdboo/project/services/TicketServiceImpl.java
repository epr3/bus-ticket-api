package com.apdboo.project.services;

import com.apdboo.project.exceptions.*;
import com.apdboo.project.models.*;
import com.apdboo.project.repositories.BusRepository;
import com.apdboo.project.repositories.IntervalRepository;
import com.apdboo.project.repositories.RouteRepository;
import com.apdboo.project.repositories.TicketRepository;
import com.apdboo.project.requests.TicketRequest;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private IntervalRepository intervalRepository;

    @Override
    public Ticket createTicket(TicketRequest ticketRequest) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bus bus = this.busRepository.findById(ticketRequest.getBus_id()).orElseThrow(BusNotFoundException::new);
        Route route = this.routeRepository.findById(ticketRequest.getRoute_id()).orElseThrow(RouteNotFoundException::new);
        Interval interval = this.intervalRepository.findById(ticketRequest.getInterval_id()).orElseThrow(IntervalNotFoundException::new);
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
        return this.ticketRepository.save(
                Ticket.builder()
                        .ticket_date(ticketRequest.getTicket_date())
                        .code(shortId)
                        .total(total)
                        .bus(bus)
                        .route(route)
                        .interval(interval)
                        .passenger(currentUser.getPassenger())
                        .build()
        );
    }

    @Override
    public Ticket updateTicket(Long id, TicketRequest ticketRequest) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        Bus bus = this.busRepository.findById(ticketRequest.getBus_id()).orElseThrow(BusNotFoundException::new);
        Route route = this.routeRepository.findById(ticketRequest.getRoute_id()).orElseThrow(RouteNotFoundException::new);
        Interval interval = this.intervalRepository.findById(ticketRequest.getInterval_id()).orElseThrow(IntervalNotFoundException::new);
        ticket.setBus(bus);
        ticket.setRoute(route);
        ticket.setInterval(interval);
        ticket.setTicket_date(ticketRequest.getTicket_date());

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
        return ticket;
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(CityNotFoundException::new);
        this.ticketRepository.delete(ticket);
    }

    @Override
    public Ticket getTicket(Long id) {
        return this.ticketRepository.findById(id).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public List<Ticket> getTickets() {
        return this.ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsForUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.ticketRepository.getTicketsByPassengerId(currentUser.getPassenger().getId());
    }
}
