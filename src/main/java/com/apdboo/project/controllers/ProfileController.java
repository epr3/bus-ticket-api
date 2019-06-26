package com.apdboo.project.controllers;

import com.apdboo.project.models.Passenger;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    PassengerRepository repository;

    @GetMapping("/me")
    public ResponseEntity profile() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Passenger passenger = this.repository.getPassengerByUserId(currentUser.getId());
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }
}
