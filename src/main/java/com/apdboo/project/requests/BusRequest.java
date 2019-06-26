package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRequest {
    @NotBlank(message = "Plate number cannot be blank!")
    @NotNull(message = "Plate number cannot be missing or empty")
    private String plateNo;

    @NotBlank(message = "Bus make cannot be blank!")
    @NotNull(message = "Bus make cannot be missing or empty")
    private String busMake;

    @NotBlank(message = "Bus model cannot be blank!")
    @NotNull(message = "Bus model cannot be missing or empty")
    private String busModel;

    private List<Long> amenities;
}
