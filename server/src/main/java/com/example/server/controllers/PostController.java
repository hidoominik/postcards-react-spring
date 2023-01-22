package com.example.server.controllers;

import com.example.server.entities.Post;
import com.example.server.entities.User;
import com.example.server.services.PostService;
import com.example.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        if(post.getMessage().length() == 0) return new ResponseEntity<>("Message cannot be empty!", HttpStatus.BAD_REQUEST);
        if(post.getTitle().length() == 0) return new ResponseEntity<>("Title cannot be empty!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(postService.save(post));
    }


    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.ok(postService.findAll());
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByName(name).orElseThrow(RuntimeException::new);
        Post post = postService.findById(id).orElseThrow(RuntimeException::new);
        if(!user.equals(post.getCreator()))return new ResponseEntity<>("Only creator can delete post!", HttpStatus.BAD_REQUEST);

        postService.delete(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post updatedPost){

        if(updatedPost.getMessage().length() == 0) return new ResponseEntity<>("Message cannot be empty!", HttpStatus.BAD_REQUEST);
        if(updatedPost.getTitle().length() == 0) return new ResponseEntity<>("Title cannot be empty!", HttpStatus.BAD_REQUEST);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByName(name).orElseThrow(RuntimeException::new);
        Post post = postService.findById(id).orElseThrow(RuntimeException::new);
        if(!user.equals(post.getCreator()))return new ResponseEntity<>("Only creator can edit post!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(postService.edit(updatedPost, id), HttpStatus.OK);
    }

}
