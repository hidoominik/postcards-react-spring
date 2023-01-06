package com.example.server.services;

import com.example.server.entities.Post;

import java.util.Optional;

public interface PostService {
    Post save(Post post);
    Post edit(Post previousPost, Post editedPost);
    Post editStatus(Post task);
    void delete(Post task);

    Optional<Post> findById(int id);
}
