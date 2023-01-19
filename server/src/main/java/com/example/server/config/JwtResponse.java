package com.example.server.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String login;

    public JwtResponse(String accessToken, String login) {
        this.token = accessToken;
        this.login = login;
    }
}
