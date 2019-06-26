package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {
    @NotBlank(message = "Name cannot be blank!")
    @NotNull(message = "Name cannot be missing or empty")
    private String name;
}
