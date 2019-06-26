package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {
    @NotBlank(message = "Price cannot be blank!")
    @NotNull(message = "Price be missing or empty")
    private Float price;

    @NotBlank(message = "Distance cannot be blank!")
    @NotNull(message = "Distance be missing or empty")
    private Integer distance;

    @NotBlank(message = "Start city cannot be blank!")
    @NotNull(message = "Start city be missing or empty")
    private Long startCityId;

    @NotBlank(message = "End city cannot be blank!")
    @NotNull(message = "End city be missing or empty")
    private Long endCityId;
}
