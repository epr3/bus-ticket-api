package com.apdboo.project.services;

import com.apdboo.project.exceptions.AmenityNotFoundException;
import com.apdboo.project.models.Amenity;
import com.apdboo.project.repositories.AmenityRepository;
import com.apdboo.project.requests.AmenityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {
    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public Amenity createAmenity(AmenityRequest amenityRequest) {
        return this.amenityRepository.save(Amenity.builder()
                .name(amenityRequest.getName())
                .icon(amenityRequest.getIcon())
                .priceModifier(amenityRequest.getPriceModifier())
                .priceModifierType(amenityRequest.getPriceModifierType())
                .build()
        );
    }

    @Override
    public Amenity updateAmenity(Long id, AmenityRequest amenityRequest) {
        Amenity amenity = this.amenityRepository.findById(id).orElseThrow(AmenityNotFoundException::new);
        amenity.setName(amenityRequest.getName());
        amenity.setIcon(amenityRequest.getIcon());
        amenity.setPriceModifier(amenityRequest.getPriceModifier());
        amenity.setPriceModifierType(amenityRequest.getPriceModifierType());
        this.amenityRepository.save(amenity);
        return amenity;
    }

    @Override
    public void deleteAmenity(Long id) {
        Amenity amenity = this.amenityRepository.findById(id).orElseThrow(AmenityNotFoundException::new);
        this.amenityRepository.delete(amenity);
    }

    @Override
    public Amenity getAmenity(Long id) {
        return this.amenityRepository.findById(id).orElseThrow(AmenityNotFoundException::new);
    }

    @Override
    public List<Amenity> getAmenities() {
        return null;
    }
}
