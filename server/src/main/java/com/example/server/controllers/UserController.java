package com.example.server.controllers;

import com.example.server.config.JwtProvider;
import com.example.server.entities.JwtResponse;
import com.example.server.entities.LoginRequest;
import com.example.server.entities.User;
import com.example.server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                          UserService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping( "/user/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(email).orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(new JwtResponse(user, jwtToken));
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> register(@RequestBody LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String firstName = loginRequest.getFirstName();
        if(!firstName.matches("[A-Z][a-z]*")) return new ResponseEntity<>("First name have bad format!", HttpStatus.BAD_REQUEST);

        String lastName = loginRequest.getLastName();
        if(!lastName.matches("[A-Z][a-z]*")) return new ResponseEntity<>("Last name have bad format!", HttpStatus.BAD_REQUEST);

        Optional<User> user = userService.findByEmail(email);
        if(user.isPresent()) return new ResponseEntity<>("Current email is already in use!", HttpStatus.BAD_REQUEST);
        String password = loginRequest.getPassword();
        String confirmPassword = loginRequest.getConfirmPassword();
        if(!password.equals(confirmPassword)) return new ResponseEntity<>("Passwords dont match!", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(userService.save(loginRequest));
    }
}
