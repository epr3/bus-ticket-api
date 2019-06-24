package com.apdboo.project.exceptions;

public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException() {
    }

    public TweetNotFoundException(Long id) {
        super("Tweet: " + id + " not found.");
    }
}
