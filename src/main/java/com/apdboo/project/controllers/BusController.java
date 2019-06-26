package com.apdboo.project.controllers;

import com.apdboo.project.requests.BusRequest;
import com.apdboo.project.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;


    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.busService.getBuses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.busService.getBus(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody BusRequest form) {
        return new ResponseEntity<>(this.busService.createBus(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody BusRequest form) {
        return new ResponseEntity<>(this.busService.updateBus(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.busService.deleteBus(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
