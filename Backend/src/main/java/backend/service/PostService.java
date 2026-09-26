package backend.service;

import backend.exception.ResourceNotFoundException;
import backend.model.Post;
import backend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // CREATE
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // READ ALL
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // READ ONE
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // UPDATE
    public Post updatePost(Long id, Post post) {

        Post existingPost = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        existingPost.setContent(post.getContent());
        existingPost.setPlatform(post.getPlatform());

        return postRepository.save(existingPost);
    }

    // DELETE
    public void deletePost(Long id) {

        Post existingPost = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + id
                        )
                );

        postRepository.delete(existingPost);
    }
}