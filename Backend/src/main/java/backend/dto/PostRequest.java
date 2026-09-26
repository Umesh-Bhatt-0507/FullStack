package backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequest {

    @NotBlank(message = "Content cannot be empty")
    @Size(max = 500, message = "Content cannot exceed 500 characters")
    private String content;

    @NotBlank(message = "Platform cannot be empty")
    private String platform;

    public PostRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}