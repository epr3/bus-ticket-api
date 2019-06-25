package com.apdboo.project.utils;

import com.apdboo.project.models.User;
import com.apdboo.project.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
@Slf4j
public class LoadDatabase implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var user = this.userRepository.save(User.builder()
                .email("test@test.com")
                .password(this.passwordEncoder.encode("password"))
                .roles(Collections.singletonList("ADMIN"))
                .build()
        );

        log.debug("printing all users...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
