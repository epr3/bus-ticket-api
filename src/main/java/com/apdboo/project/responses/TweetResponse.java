package com.apdboo.project.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponse implements Serializable {
    private Long id;
    private String tweet;
    private Long user_id;
    private UserResponse user;
}
