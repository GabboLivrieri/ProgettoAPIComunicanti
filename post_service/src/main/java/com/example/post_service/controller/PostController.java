package com.example.post_service.controller;

import com.example.post_service.DTO.UserDTO;
import com.example.post_service.entity.PostEntity;
import com.example.post_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok("Post eliminato con successo");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<?> getUserForPost(@PathVariable Long id) {
        try {
            PostEntity post = postService.getPostById(id);
            String baseUrl = userServiceUrl.endsWith("/") ? userServiceUrl : userServiceUrl + "/";
            String url = baseUrl + post.getUserId();
            UserDTO user = restTemplate.getForObject(url, UserDTO.class);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato per ID " + post.getUserId());
            }

            return ResponseEntity.ok(user);

        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Errore nel contattare il servizio utenti: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post non trovato per ID " + id);
        }
    }}

