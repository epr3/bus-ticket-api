package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {
    @NotNull(message = "Price be missing or empty")
    private Float price;

    @NotNull(message = "Distance be missing or empty")
    private Integer distance;

    @NotNull(message = "Start city be missing or empty")
    private Long startCityId;

    @NotNull(message = "End city be missing or empty")
    private Long endCityId;
}
