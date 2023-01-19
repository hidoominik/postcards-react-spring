package com.example.server.controllers;

import com.example.server.entities.Post;
import com.example.server.entities.User;
import com.example.server.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post){

        return ResponseEntity.ok(postService.save(post));
    }


    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.ok(postService.findAll());
    }


}
