package com.apdboo.project.controllers;

import com.apdboo.project.requests.AmenityRequest;
import com.apdboo.project.services.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;


    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.amenityService.getAmenities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.amenityService.getAmenity(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody AmenityRequest form) {
        return new ResponseEntity<>(this.amenityService.createAmenity(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody AmenityRequest form) {
        return new ResponseEntity<>(this.amenityService.updateAmenity(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.amenityService.deleteAmenity(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
