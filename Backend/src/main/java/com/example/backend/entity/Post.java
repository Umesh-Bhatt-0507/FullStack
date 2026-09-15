package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Post cannot be empty")
    @Size(min = 5, max = 3000, message = "Post must be between 5 and 3000 characters")
    private String content;

    @NotBlank(message = "Platform is required")
    private String platform;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPlatform() {
        return platform;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}