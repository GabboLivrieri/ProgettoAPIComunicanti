package com.example.post_service.controller;

import com.example.post_service.entity.PostEntity;
import com.example.post_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostEntity>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<PostEntity> create(@RequestBody PostEntity post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostEntity> update(@PathVariable Long id, @RequestBody PostEntity post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
