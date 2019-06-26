package com.apdboo.project.services;

import com.apdboo.project.exceptions.BusNotFoundException;
import com.apdboo.project.exceptions.IntervalNotFoundException;
import com.apdboo.project.exceptions.ItineraryNotFoundException;
import com.apdboo.project.exceptions.RouteNotFoundException;
import com.apdboo.project.models.Bus;
import com.apdboo.project.models.Interval;
import com.apdboo.project.models.Itinerary;
import com.apdboo.project.models.Route;
import com.apdboo.project.repositories.BusRepository;
import com.apdboo.project.repositories.IntervalRepository;
import com.apdboo.project.repositories.ItineraryRepository;
import com.apdboo.project.repositories.RouteRepository;
import com.apdboo.project.requests.ItineraryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private IntervalRepository intervalRepository;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Override
    public Itinerary createItinerary(ItineraryRequest itineraryRequest) {
        Bus bus = this.busRepository.findById(itineraryRequest.getBusId()).orElseThrow(BusNotFoundException::new);
        Route route = this.routeRepository.findById(itineraryRequest.getRouteId()).orElseThrow(RouteNotFoundException::new);
        Interval interval = this.intervalRepository.findById(itineraryRequest.getIntervalId()).orElseThrow(IntervalNotFoundException::new);
        return this.itineraryRepository.save(
                Itinerary.builder()
                        .bus(bus)
                        .interval(interval)
                        .route(route)
                        .build());
    }

    @Override
    public Itinerary updateItinerary(Long id, ItineraryRequest itineraryRequest) {
        Bus bus = this.busRepository.findById(itineraryRequest.getBusId()).orElseThrow(BusNotFoundException::new);
        Route route = this.routeRepository.findById(itineraryRequest.getRouteId()).orElseThrow(RouteNotFoundException::new);
        Interval interval = this.intervalRepository.findById(itineraryRequest.getIntervalId()).orElseThrow(IntervalNotFoundException::new);
        Itinerary itinerary = this.itineraryRepository.findById(id).orElseThrow(ItineraryNotFoundException::new);
        itinerary.setBus(bus);
        itinerary.setInterval(interval);
        itinerary.setRoute(route);
        this.itineraryRepository.save(itinerary);
        return itinerary;
    }

    @Override
    public void deleteItinerary(Long id) {
        Itinerary itinerary = this.itineraryRepository.findById(id).orElseThrow(ItineraryNotFoundException::new);
        this.itineraryRepository.delete(itinerary);
    }

    @Override
    public Itinerary getItinerary(Long id) {
        return this.itineraryRepository.findById(id).orElseThrow(ItineraryNotFoundException::new);
    }

    @Override
    public List<Itinerary> getItineraries() {
        return this.itineraryRepository.findAll();
    }
}
