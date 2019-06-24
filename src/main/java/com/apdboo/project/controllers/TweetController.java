package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.TweetNotFoundException;
import com.apdboo.project.forms.TweetRequest;
import com.apdboo.project.models.Tweet;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.TweetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private TweetRepository repository;

    public TweetController(TweetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity all() {
        return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.repository.findById(id).orElseThrow(() -> new TweetNotFoundException()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody TweetRequest form) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tweet saved = this.repository.save(Tweet.builder().tweet(form.getTweet()).user(currentUser).build());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody TweetRequest form) {
        Tweet existing = this.repository.findById(id).orElseThrow(() -> new TweetNotFoundException());
        existing.setTweet(form.getTweet());
        this.repository.save(existing);
        return new ResponseEntity<>(existing, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Tweet existing = this.repository.findById(id).orElseThrow(() -> new TweetNotFoundException());
        this.repository.delete(existing);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
