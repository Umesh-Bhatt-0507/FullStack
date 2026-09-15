package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.entity.Post;
import com.example.backend.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    // GET all posts
    @GetMapping
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Posts fetched successfully",
                        service.getAllPosts()
                )
        );
    }

    // GET one post
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> getPost(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Post fetched successfully",
                        service.getPost(id)
                )
        );
    }

    // CREATE post
    @PostMapping
    public ResponseEntity<ApiResponse<Post>> createPost(
            @Valid @RequestBody Post post) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Post created successfully",
                                service.createPost(post)
                        )
                );
    }

    // UPDATE post
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody Post post) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Post updated successfully",
                        service.updatePost(id, post)
                )
        );
    }

    // DELETE post
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id) {

        service.deletePost(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Post deleted successfully",
                        null
                )
        );
    }
}