package com.apdboo.project.controllers;


import com.apdboo.project.exceptions.CityNotFoundException;
import com.apdboo.project.models.City;
import com.apdboo.project.repositories.CityRepository;
import com.apdboo.project.requests.CityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private CityRepository repository;

    public CityController(CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity all() {
        List<City> cityList = this.repository.findAll();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        City city = this.repository.findById(id).orElseThrow(CityNotFoundException::new);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody CityRequest form) {
        City city = this.repository.save(City.builder().name(form.getName()).build());
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody CityRequest form) {
        City city = this.repository.findById(id).orElseThrow(CityNotFoundException::new);
        city.setName(form.getName());
        this.repository.save(city);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        City city = this.repository.findById(id).orElseThrow(CityNotFoundException::new);
        this.repository.delete(city);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
