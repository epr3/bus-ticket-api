package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRequest {
    @NotBlank(message = "Bus cannot be blank!")
    @NotNull(message = "Bus cannot be missing or empty")
    private Long busId;

    @NotBlank(message = "Route cannot be blank!")
    @NotNull(message = "Route cannot be missing or empty")
    private Long routeId;

    @NotBlank(message = "Interval cannot be blank!")
    @NotNull(message = "Interval cannot be missing or empty")
    private Long intervalId;
}