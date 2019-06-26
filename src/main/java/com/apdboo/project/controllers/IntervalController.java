package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.IntervalNotFoundException;
import com.apdboo.project.models.Interval;
import com.apdboo.project.repositories.IntervalRepository;
import com.apdboo.project.requests.IntervalRequest;
import com.apdboo.project.services.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/intervals")
public class IntervalController {
    @Autowired
    private IntervalService intervalService;

    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.intervalService.getIntervals(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.intervalService.getInterval(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody IntervalRequest form) {
        return new ResponseEntity<>(this.intervalService.createInterval(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody IntervalRequest form) {
        return new ResponseEntity<>(this.intervalService.updateInterval(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.intervalService.deleteInterval(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
