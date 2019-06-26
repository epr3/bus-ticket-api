package com.apdboo.project.services;

import com.apdboo.project.models.User;
import com.apdboo.project.requests.AuthenticationRequest;

public interface UserService {
    User createUser(AuthenticationRequest authenticationRequest);
    User getUserByEmail(String email);
}
