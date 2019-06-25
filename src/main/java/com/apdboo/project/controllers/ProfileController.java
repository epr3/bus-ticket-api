package com.apdboo.project.controllers;

import com.apdboo.project.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/me")
    public ResponseEntity profile() {
        Map<Object, Object> model = new HashMap<>();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("name", currentUser.getName());
        model.put("id", currentUser.getId());
        model.put("email", currentUser.getEmail());
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
