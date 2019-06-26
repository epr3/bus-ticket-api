package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.AmenityNotFoundException;
import com.apdboo.project.models.Amenity;
import com.apdboo.project.repositories.AmenityRepository;
import com.apdboo.project.requests.AmenityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

    @Autowired
    private AmenityRepository repository;


    @GetMapping("")
    public ResponseEntity all() {
        List<Amenity> amenityList = this.repository.findAll();
        return new ResponseEntity<>(amenityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Amenity amenity = this.repository.findById(id).orElseThrow(AmenityNotFoundException::new);
        return new ResponseEntity<>(amenity, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody AmenityRequest form) {
        Amenity amenity = this.repository.save(Amenity.builder()
                .name(form.getName())
                .icon(form.getIcon())
                .priceModifier(form.getPriceModifier())
                .priceModifierType(form.getPriceModifierType())
                .build()
        );
        return new ResponseEntity<>(amenity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody AmenityRequest form) {
        Amenity amenity = this.repository.findById(id).orElseThrow(AmenityNotFoundException::new);
        amenity.setName(form.getName());
        amenity.setIcon(form.getIcon());
        amenity.setPriceModifier(form.getPriceModifier());
        amenity.setPriceModifierType(form.getPriceModifierType());
        this.repository.save(amenity);
        return new ResponseEntity<>(amenity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Amenity amenity = this.repository.findById(id).orElseThrow(AmenityNotFoundException::new);
        this.repository.delete(amenity);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
