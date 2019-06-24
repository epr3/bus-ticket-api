package com.apdboo.project.controllers;

import com.apdboo.project.repositories.UserRepository;
import com.apdboo.project.security.jwt.JwtTokenProvider;
import com.apdboo.project.web.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest data) {
        try {
            String email = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
            String token = jwtTokenProvider.createToken(
                    email,
                    this.repository.findByEmail(email)
                            .orElseThrow(
                                    () -> new UsernameNotFoundException("Email " + email + " not found.")).getRoles()
            );
            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials supplied");
        }
    }
}
