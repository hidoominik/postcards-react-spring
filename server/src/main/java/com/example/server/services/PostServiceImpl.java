package com.example.server.services;

import com.example.server.entities.Post;
import com.example.server.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post edit(Post previousPost, Post editedPost) {
        return null;
    }

    @Override
    public Post editStatus(Post task) {
        return null;
    }

    @Override
    public void delete(Post task) {

    }

    @Override
    public Optional<Post> findById(int id) {
        return Optional.empty();
    }

    public List<Post> findAll(){

        return postRepository.findAll();
    }
}
