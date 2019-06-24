package com.apdboo.project.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private static final long serialVersionUID = -6986746375915710855L;
    private String email;
    private String password;
}
