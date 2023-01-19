package com.example.server.config;

import com.example.server.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private User user;

    public JwtResponse(User user,String accessToken ) {
        this.token = accessToken;
        this.user = user;
    }
}
