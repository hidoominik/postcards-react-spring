package com.example.server.services;

import com.example.server.entities.Post;
import com.example.server.entities.User;
import com.example.server.entities.UserPrinciple;
import com.example.server.repositories.PostRepository;
import com.example.server.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;


    }

    @Override
    public Post save(Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByName(name).orElseThrow(RuntimeException::new);
//        post.setCreator(user.getId());
        post.setCreator(user);
        post.setCreatedAt(LocalDateTime.now());
        user.addPost(post);
        return postRepository.save(post);
    }

    @Override
    public Post edit(Post newPostData, Long editedPostId) {
        Post post = postRepository.findById(editedPostId).orElseThrow(RuntimeException::new);
        post.setName(newPostData.getName());
        post.setTags(newPostData.getTags());
        post.setMessage(newPostData.getMessage());
        post.setTitle(newPostData.getTitle());
        post.setSelectedFile(newPostData.getSelectedFile());


        return postRepository.save(post);
    }

    @Override
    public Post editStatus(Post task) {
        return null;
    }

    @Override
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        postRepository.delete(post);

    }

    @Override
    public Optional<Post> findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post;
    }


    public List<Post> findAll(){

        return postRepository.findAll();
    }
}
