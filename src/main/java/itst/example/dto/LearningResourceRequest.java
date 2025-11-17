package itst.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LearningResourceRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @NotBlank
    private String url;
    private int views;
}
