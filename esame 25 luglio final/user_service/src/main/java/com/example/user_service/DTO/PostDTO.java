package com.example.user_service.DTO;

import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private Long userId;
    private String content;
}
