package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest extends AuthenticationRequest {
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
