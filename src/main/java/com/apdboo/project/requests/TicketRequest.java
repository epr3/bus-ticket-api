package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
    @NotBlank(message = "Ticket date cannot be blank!")
    @NotNull(message = "Ticket date cannot be missing or empty")
    private Date ticket_date;

    @NotBlank(message = "Bus cannot be blank!")
    @NotNull(message = "Bus cannot be missing or empty")
    private Long bus_id;

    @NotBlank(message = "Route cannot be blank!")
    @NotNull(message = "Route cannot be missing or empty")
    private Long route_id;

    @NotBlank(message = "Interval cannot be blank!")
    @NotNull(message = "Interval cannot be missing or empty")
    private Long interval_id;
}
