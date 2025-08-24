package com.sprint4.team4.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String userId;
    private String userName;
    private String token;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
}
