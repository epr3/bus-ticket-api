package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.TweetNotFoundException;
import com.apdboo.project.forms.TweetForm;
import com.apdboo.project.models.Tweet;
import com.apdboo.project.repositories.TweetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private TweetRepository repository;

    public TweetController(TweetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity all() {
        return ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ok(this.repository.findById(id).orElseThrow(() -> new TweetNotFoundException()));
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody TweetForm form, HttpServletRequest request) {
        Tweet saved = this.repository.save(Tweet.builder().tweet(form.getTweet()).build());
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/tweets/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TweetForm form) {
        Tweet existing = this.repository.findById(id).orElseThrow(() -> new TweetNotFoundException());
        existing.setTweet(form.getTweet());
        this.repository.save(existing);
        return ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Tweet existing = this.repository.findById(id).orElseThrow(() -> new TweetNotFoundException());
        this.repository.delete(existing);
        return  noContent().build();
    }

}
