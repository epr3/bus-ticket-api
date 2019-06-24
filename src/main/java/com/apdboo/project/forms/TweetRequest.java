package com.apdboo.project.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetRequest {
    @NotNull(message = "Tweet cannot be missing or empty")
    @Size(max = 140, message = "Tweet cannot be larger than 140 characters")
    private String tweet;
}
