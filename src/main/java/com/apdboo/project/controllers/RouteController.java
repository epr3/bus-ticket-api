package com.apdboo.project.controllers;

import com.apdboo.project.models.Route;
import com.apdboo.project.requests.RouteRequest;
import com.apdboo.project.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.routeService.getRoutes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.routeService.getRoute(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody RouteRequest form) {
        return new ResponseEntity<>(this.routeService.createRoute(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody RouteRequest form) {
        return new ResponseEntity<>(this.routeService.updateRoute(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.routeService.deleteRoute(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
