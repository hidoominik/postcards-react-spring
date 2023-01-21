package com.example.server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Column(unique = true, nullable = false, length = 45)
    @Size(min = 5, max = 45, message = "{user.email.size}")
    @NotBlank(message = "{user.email.notBlank}")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 1000)
    @NotBlank(message = "{user.password.notBlank}")
    private String password;

    private String confirmPassword;
    private String firstName;
    private String lastName;
}
