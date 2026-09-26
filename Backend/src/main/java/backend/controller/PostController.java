package backend.controller;

import backend.dto.PostRequest;
import backend.exception.ResourceNotFoundException;
import backend.model.Post;
import backend.response.ApiResponse;
import backend.service.PostService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Post>> createPost(
            @Valid @RequestBody PostRequest request) {

        Post post = new Post(
                request.getContent(),
                request.getPlatform()
        );

        Post createdPost = postService.createPost(post);

        ApiResponse<Post> response = new ApiResponse<>(
                true,
                "Post created successfully",
                createdPost
        );

        return ResponseEntity.ok(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {

        List<Post> posts = postService.getAllPosts();

        ApiResponse<List<Post>> response = new ApiResponse<>(
                true,
                "Posts fetched successfully",
                posts
        );

        return ResponseEntity.ok(response);
    }

    // READ ONE
    @GetMapping("/{id}")
public ResponseEntity<ApiResponse<Post>> getPostById(
        @PathVariable Long id) {

    Post post = postService.getPostById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Post not found with id: " + id
                    )
            );

    ApiResponse<Post> response = new ApiResponse<>(
            true,
            "Post fetched successfully",
            post
    );

    return ResponseEntity.ok(response);
}

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest request) {

        Post post = new Post(
                request.getContent(),
                request.getPlatform()
        );

        Post updatedPost = postService.updatePost(id, post);

        ApiResponse<Post> response = new ApiResponse<>(
                true,
                "Post updated successfully",
                updatedPost
        );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id) {

        postService.deletePost(id);

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Post deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}