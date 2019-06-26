package com.apdboo.project.services;

import com.apdboo.project.models.Amenity;
import com.apdboo.project.requests.AmenityRequest;

import java.util.List;

public interface AmenityService {
    Amenity createAmenity(AmenityRequest amenityRequest);
    Amenity updateAmenity(Long id, AmenityRequest amenityRequest);
    void deleteAmenity(Long id);
    Amenity getAmenity(Long id);
    List<Amenity> getAmenities();
}
