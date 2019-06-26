package com.apdboo.project.controllers;

import com.apdboo.project.requests.PassengerRequest;
import com.apdboo.project.models.User;
import com.apdboo.project.requests.AuthenticationRequest;
import com.apdboo.project.security.jwt.JwtTokenProvider;
import com.apdboo.project.services.PassengerService;
import com.apdboo.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerService passengerService;

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final String token = jwtTokenProvider.createToken(authenticationRequest.getEmail(), this.userService.getUserByEmail(authenticationRequest.getEmail()).getRoles());

        // Return the token
        Map<Object, Object> model = new HashMap<>();
        model.put("email", authenticationRequest.getEmail());
        model.put("token", token);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody PassengerRequest passengerRequest) {
        User user = this.userService.createUser(passengerRequest);
        this.passengerService.createPassenger(passengerRequest);
        String token = jwtTokenProvider.createToken(
                user.getEmail(),
                this.userService.getUserByEmail(passengerRequest.getEmail()).getRoles()
        );
        Map<Object, Object> model = new HashMap<>();
        model.put("email", user.getEmail());
        model.put("token", token);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
