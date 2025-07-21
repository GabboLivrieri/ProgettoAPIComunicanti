package com.example.post_service.controller;

import com.example.post_service.entity.PostEntity;
import com.example.post_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostEntity post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
}
