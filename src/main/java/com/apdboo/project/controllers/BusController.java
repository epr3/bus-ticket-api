package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.BusNotFoundException;
import com.apdboo.project.models.Amenity;
import com.apdboo.project.models.Bus;
import com.apdboo.project.repositories.AmenityRepository;
import com.apdboo.project.repositories.BusRepository;
import com.apdboo.project.requests.BusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private AmenityRepository amenityRepository;


    @GetMapping("")
    public ResponseEntity all() {
        List<Bus> busList = this.busRepository.findAll();
        return new ResponseEntity<>(busList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Bus city = this.busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody BusRequest form) {
        List<Amenity> amenities = this.amenityRepository.getAmenitiesByIds(form.getAmenities());
        Bus bus = this.busRepository.save(
                Bus.builder()
                        .plateNo(form.getPlateNo())
                        .busMake(form.getBusModel())
                        .busModel(form.getBusModel())
                        .amenities(amenities)
                .build());
        return new ResponseEntity<>(bus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody BusRequest form) {
        List<Amenity> amenities = this.amenityRepository.getAmenitiesByIds(form.getAmenities());
        Bus bus = this.busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        bus.setBusMake(form.getBusMake());
        bus.setBusModel(form.getBusModel());
        bus.setPlateNo(form.getPlateNo());
        bus.setAmenities(amenities);
        this.busRepository.save(bus);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Bus bus = this.busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        this.busRepository.delete(bus);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
