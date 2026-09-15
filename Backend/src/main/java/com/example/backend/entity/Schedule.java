package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Post is required")
    private String post;

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotNull(message = "Scheduled time is required")
    private LocalDateTime scheduledTime;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public String getPlatform() {
        return platform;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}