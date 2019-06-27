package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRequest {
    @NotNull(message = "Bus cannot be missing or empty")
    private Long busId;

    @NotNull(message = "Route cannot be missing or empty")
    private Long routeId;

    @NotNull(message = "Interval cannot be missing or empty")
    private Long intervalId;
}
