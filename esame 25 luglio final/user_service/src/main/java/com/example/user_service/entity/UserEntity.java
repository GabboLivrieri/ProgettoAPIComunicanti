package com.example.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
