package com.apdboo.project.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetRequest {
    @NotBlank(message = "Tweet cannot be blank!")
    @NotNull(message = "Tweet cannot be missing or empty")
    @Size(max = 140, message = "Tweet cannot be larger than 140 characters")
    private String tweet;
}
