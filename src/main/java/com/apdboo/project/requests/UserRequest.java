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
public class UserRequest {
    @NotBlank(message = "E-mail cannot be blank!")
    @NotNull(message = "E-mail cannot be missing or empty")
    @Email(message = "E-mail must be a valid format")
    private String email;

    @NotBlank(message = "Password cannot be blank!")
    @NotNull(message = "Password cannot be missing or empty")
    private String password;

    @NotBlank(message = "Name cannot be blank!")
    @NotNull(message = "Name cannot be missing or empty")
    private String name;

    @NotBlank(message = "Surname cannot be blank!")
    @NotNull(message = "Surname cannot be missing or empty")
    private String surname;

    @NotBlank(message = "Telephone cannot be blank!")
    @NotNull(message = "Telephone cannot be missing or empty")
    private String telephone;
}
