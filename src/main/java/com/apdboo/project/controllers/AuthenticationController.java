package com.apdboo.project.controllers;

import com.apdboo.project.models.Passenger;
import com.apdboo.project.repositories.PassengerRepository;
import com.apdboo.project.requests.UserRequest;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.UserRepository;
import com.apdboo.project.security.CustomUserDetailsService;
import com.apdboo.project.requests.AuthenticationRequest;
import com.apdboo.project.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PassengerRepository passengerRepository;

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
        final String token = jwtTokenProvider.createToken(authenticationRequest.getEmail(), this.userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Email " + authenticationRequest.getEmail() + " not found.")).getRoles());

        // Return the token
        Map<Object, Object> model = new HashMap<>();
        model.put("email", authenticationRequest.getEmail());
        model.put("token", token);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRequest userRequest) {
        User user = this.userRepository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .password(passwordEncoder.encode(userRequest.getPassword()))
                        .roles(Collections.singletonList("PASSENGER"))
                        .build());
        this.passengerRepository.save(
                Passenger.builder()
                        .name(userRequest.getName())
                        .surname(userRequest
                                .getSurname())
                        .telephone(userRequest.getTelephone())
                        .user(user)
                        .build()
        );
        String token = jwtTokenProvider.createToken(
                user.getEmail(),
                this.userRepository.findByEmail(user.getEmail())
                        .orElseThrow(
                                () -> new UsernameNotFoundException("Email " + user.getEmail() + " not found.")).getRoles()
        );
        Map<Object, Object> model = new HashMap<>();
        model.put("email", user.getEmail());
        model.put("token", token);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
