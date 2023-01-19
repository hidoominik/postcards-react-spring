package com.example.server.services;

import com.example.server.entities.User;
import com.example.server.entities.UserPrinciple;
import com.example.server.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
//    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) {
        User user = userRepository.findByEmail(s).orElseThrow(RuntimeException::new);

        return UserPrinciple.build(user);
    }
}
