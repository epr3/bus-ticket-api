package com.apdboo.project.controllers;

import com.apdboo.project.requests.CityRequest;
import com.apdboo.project.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;


    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.cityService.getCities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.cityService.getCity(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody CityRequest form) {
        return new ResponseEntity<>(this.cityService.createCity(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody CityRequest form) {
        return new ResponseEntity<>(this.cityService.updateCity(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.cityService.deleteCity(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
