package com.apdboo.project.utils;

import com.apdboo.project.models.Tweet;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.TweetRepository;
import com.apdboo.project.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class LoadDatabase implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var user = this.userRepository.save(User.builder()
                .email("test@test.com")
                .name("user")
                .password(this.passwordEncoder.encode("password"))
                .roles(Collections.singletonList("ROLE_USER"))
                .build()
        );
        this.tweetRepository.save(Tweet.builder()
                .tweet("tweet1")
                .user(user)
                .build()
        );
        this.tweetRepository.save(Tweet.builder()
                .tweet("tweet2")
                .user(user)
                .build()
        );
        this.tweetRepository.save(Tweet.builder()
                .tweet("tweet3")
                .user(user)
                .build()
        );
        log.debug("printing all users...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
