package com.example.server.services;

import com.example.server.entities.LoginRequest;
import com.example.server.entities.User;

import java.util.Optional;

public interface UserService {

//    User save(User user);
    Optional<User> findByEmail(String email);
    User save(LoginRequest loginRequest);
//    Boolean existsByEmail(String email);
//    void delete(User user);
    Optional<User> findByName(String name);

}
