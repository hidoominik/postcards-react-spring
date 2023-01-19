package com.example.server.entities;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private final User user;

    public JwtResponse(User user,String jwttoken) {
        this.user = user;
        this.jwttoken = jwttoken;
    }
    public String getToken() {
        return this.jwttoken;
    }
    public User getUser(){
        return this.user;
    }
}
