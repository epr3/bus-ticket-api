package com.apdboo.project.controllers;

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
    UserRepository repository;

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
        final String token = jwtTokenProvider.createToken(authenticationRequest.getEmail(), this.repository.findByEmail(authenticationRequest.getEmail())
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
        User saved = this.repository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .password(passwordEncoder.encode(userRequest.getPassword()))
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());
        String token = jwtTokenProvider.createToken(
                saved.getEmail(),
                this.repository.findByEmail(saved.getEmail())
                        .orElseThrow(
                                () -> new UsernameNotFoundException("Email " + saved.getEmail() + " not found.")).getRoles()
        );
        Map<Object, Object> model = new HashMap<>();
        model.put("email", saved.getEmail());
        model.put("token", token);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
