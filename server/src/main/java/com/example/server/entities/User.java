package com.example.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonView
    private String name;

    @Column(unique = true, nullable = false, length = 45)
    @Size(min = 5, max = 45, message = "{user.email.size}")
    @NotBlank(message = "{user.email.notBlank}")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 1000)
    @NotBlank(message = "{user.password.notBlank}")
    private String password;

    @Transient
    private String roles = "ROLE_USER";

    @JsonIgnore
    @OneToMany(mappedBy="creator", cascade = CascadeType.ALL)
    private Set<Post> postSet = new HashSet<>();

    public void addPost(Post post) {
        postSet.add(post);
        post.setCreator(this);
    }
}
