package com.apdboo.project.security;

import com.apdboo.project.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
    }
}
