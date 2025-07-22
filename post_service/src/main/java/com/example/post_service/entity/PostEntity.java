package com.example.post_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long userId;
}
