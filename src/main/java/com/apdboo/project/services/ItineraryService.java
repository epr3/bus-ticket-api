package com.apdboo.project.services;

import com.apdboo.project.models.Itinerary;
import com.apdboo.project.requests.ItineraryRequest;

import java.util.List;

public interface ItineraryService {
    Itinerary createItinerary(ItineraryRequest itineraryRequest);
    Itinerary updateItinerary(Long id, ItineraryRequest itineraryRequest);
    void deleteItinerary(Long id);
    Itinerary getItinerary(Long id);
    List<Itinerary> getItineraries();
}
