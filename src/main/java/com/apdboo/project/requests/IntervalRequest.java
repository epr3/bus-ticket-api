package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntervalRequest {
    @NotBlank(message = "Start interval cannot be blank!")
    @NotNull(message = "Start interval cannot be missing or empty")
    private Date intervalStart;

    @NotBlank(message = "End interval cannot be blank!")
    @NotNull(message = "End interval cannot be missing or empty")
    private Date intervalEnd;
}
