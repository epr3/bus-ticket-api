package com.apdboo.project.services;

import com.apdboo.project.models.User;
import com.apdboo.project.repositories.UserRepository;
import com.apdboo.project.requests.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(AuthenticationRequest authenticationRequest) {
        return this.userRepository.save(
                User.builder()
                        .email(authenticationRequest.getEmail())
                        .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                        .roles(Collections.singletonList("PASSENGER"))
                        .build());
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Email " + email + " not found."));
    }
}
