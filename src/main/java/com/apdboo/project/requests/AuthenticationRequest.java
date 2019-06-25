package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "E-mail cannot be blank!")
    @NotNull(message = "E-mail cannot be missing or empty")
    @Email(message = "E-mail must be a valid format")
    private String email;

    @NotBlank(message = "Password cannot be blank!")
    @NotNull(message = "Password cannot be missing or empty")
    private String password;
}
