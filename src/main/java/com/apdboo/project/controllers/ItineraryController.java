package com.apdboo.project.controllers;

import com.apdboo.project.requests.ItineraryRequest;
import com.apdboo.project.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    private ItineraryService itineraryService;

    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.itineraryService.getItineraries(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.itineraryService.getItinerary(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody ItineraryRequest form) {
        return new ResponseEntity<>(this.itineraryService.createItinerary(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody ItineraryRequest form) {
        return new ResponseEntity<>(this.itineraryService.updateItinerary(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.itineraryService.deleteItinerary(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
