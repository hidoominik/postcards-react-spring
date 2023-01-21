package com.example.server.controllers;

import com.example.server.entities.Post;
import com.example.server.entities.User;
import com.example.server.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.delete(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post updatedPost){
        return new ResponseEntity<>(postService.edit(updatedPost, id), HttpStatus.OK);
    }

}
