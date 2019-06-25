package com.apdboo.project.exceptions;

import com.apdboo.project.security.jwt.InvalidJwtAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity tweetNotFound(TweetNotFoundException ex, WebRequest request) {
        log.debug("handling tweet not found...");
        return new ResponseEntity(null, NOT_FOUND);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity invalidJwtAuthentication(InvalidJwtAuthenticationException ex, WebRequest request) {
        log.debug("handling InvalidJwtAuthenticationException...");
        Map<Object, Object> model = new HashMap<>();
        model.put("message",ex.getMessage());
        return new ResponseEntity<>(model, UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentials(BadCredentialsException ex, WebRequest request) {
        log.debug("handling InvalidJwtAuthenticationException...");
        Map<Object, Object> model = new HashMap<>();
        model.put("message",ex.getMessage());
        return new ResponseEntity<>(model, UNAUTHORIZED);
    }
}
