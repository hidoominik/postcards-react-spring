package com.example.server.services;

import com.example.server.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post save(Post post);
    Post edit(Post newPostData, Long editedPostId);
    Post editStatus(Post task);
    void delete(Long postId);

    Optional<Post> findById(Long id);
    List<Post> findAll();
 }
