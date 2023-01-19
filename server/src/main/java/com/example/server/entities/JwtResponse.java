package com.example.server.entities;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private final String user;

    public JwtResponse(String jwttoken, String user) {
        this.user = user;
        this.jwttoken = jwttoken;
    }
    public String getToken() {
        return this.jwttoken;
    }
    public String getUser(){
        return this.user;
    }
}
