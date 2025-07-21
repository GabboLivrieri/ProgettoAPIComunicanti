package com.example.post_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id @GeneratedValue
    private Long id;
    private Long userId;
    private String content;
}
