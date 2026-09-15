package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post getPost(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post createPost(Post post) {
        return repository.save(post);
    }

    public Post updatePost(Long id, Post post) {

        Post oldPost = getPost(id);

        oldPost.setContent(post.getContent());
        oldPost.setPlatform(post.getPlatform());

        return repository.save(oldPost);
    }

    public void deletePost(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }

        repository.deleteById(id);
    }
}