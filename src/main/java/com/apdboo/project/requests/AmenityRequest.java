package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequest {
    @NotBlank(message = "Name cannot be blank!")
    @NotNull(message = "Name cannot be missing or empty")
    private String name;

    @NotBlank(message = "Icon cannot be blank!")
    @NotNull(message = "Icon cannot be missing or empty")
    private String icon;

    @NotBlank(message = "Price modifier cannot be blank!")
    @NotNull(message = "Price modifier cannot be missing or empty")
    private Float priceModifier;

    @NotBlank(message = "Price modifier type cannot be blank!")
    @NotNull(message = "Price modifier type cannot be missing or empty")
    private String priceModifierType;
}
