package com.example.server.services;

import com.example.server.entities.LoginRequest;
import com.example.server.entities.User;
import com.example.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(LoginRequest loginRequest) {
        User user = new User();
        user.setEmail(loginRequest.getEmail());

        user.setName(loginRequest.getFirstName() + " " + loginRequest.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(loginRequest.getPassword()));
        return userRepository.save(user);
    }
}
