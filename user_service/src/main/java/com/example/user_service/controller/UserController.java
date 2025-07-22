package com.example.user_service.controller;

import com.example.user_service.DTO.PostDTO;
import com.example.user_service.DTO.UserDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final RestTemplate restTemplate;

    @Value("${post.service.url}")
    private String postServiceUrl;

    @GetMapping
    public List<UserDTO> all() {
        return service.getAll();
    }

    @PostMapping
    public UserEntity save(@RequestBody UserEntity user) {
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getUserPosts(@PathVariable Long id) {
        try {
            String url = postServiceUrl + id;
            PostDTO[] posts = restTemplate.getForObject(url, PostDTO[].class);
            return ResponseEntity.ok(Arrays.asList(posts));
        } catch (RestClientException ex) {
            return ResponseEntity.status(503).body("Errore nel contattare il servizio post");
        }
    }
}
