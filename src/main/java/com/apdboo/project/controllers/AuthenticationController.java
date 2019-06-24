package com.apdboo.project.controllers;

import com.apdboo.project.forms.UserForm;
import com.apdboo.project.models.Tweet;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.UserRepository;
import com.apdboo.project.security.jwt.JwtTokenProvider;
import com.apdboo.project.web.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials supplied");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserForm userForm) {
        User saved = this.repository.save(
                User.builder()
                        .name(userForm.getName())
                        .email(userForm.getEmail())
                        .password(passwordEncoder.encode(userForm.getPassword()))
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
