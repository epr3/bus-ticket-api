package com.apdboo.project.controllers;

import com.apdboo.project.exceptions.TweetNotFoundException;
import com.apdboo.project.requests.TweetRequest;
import com.apdboo.project.models.Tweet;
import com.apdboo.project.models.User;
import com.apdboo.project.repositories.TweetRepository;
import com.apdboo.project.responses.TweetResponse;
import com.apdboo.project.responses.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private TweetRepository repository;

    public TweetController(TweetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public ResponseEntity all() {
        List<Tweet> tweetList = this.repository.findAll();
        List<TweetResponse> tweetResponseList = tweetList.stream().map(item ->
                new TweetResponse(
                        item.getId(),
                        item.getTweet(),
                        item.getUser_id(),
                        new UserResponse(item.getUser())
                )
        ).collect(Collectors.toList());
        return new ResponseEntity<>(tweetResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Tweet tweet = this.repository.findById(id).orElseThrow(TweetNotFoundException::new);
        return new ResponseEntity<>(new TweetResponse(tweet.getId(), tweet.getTweet(), tweet.getUser_id(), new UserResponse(tweet.getUser())), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@Valid @RequestBody TweetRequest form) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tweet tweet = this.repository.save(Tweet.builder().tweet(form.getTweet()).user(currentUser).build());
        return new ResponseEntity<>(new TweetResponse(tweet.getId(), tweet.getTweet(), tweet.getUser_id(), new UserResponse(tweet.getUser())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody TweetRequest form) {
        Tweet tweet = this.repository.findById(id).orElseThrow(TweetNotFoundException::new);
        tweet.setTweet(form.getTweet());
        this.repository.save(tweet);
        return new ResponseEntity<>(new TweetResponse(tweet.getId(), tweet.getTweet(), tweet.getUser_id(), new UserResponse(tweet.getUser())), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Tweet tweet = this.repository.findById(id).orElseThrow(TweetNotFoundException::new);
        this.repository.delete(tweet);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
