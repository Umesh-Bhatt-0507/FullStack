package backend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ScheduleRequest {

    @NotNull(message = "Post ID cannot be null")
    private Long postId;

    @NotNull(message = "Scheduled date and time cannot be null")
    @Future(message = "Scheduled date and time must be in the future")
    private LocalDateTime scheduledAt;

    public ScheduleRequest() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }
}