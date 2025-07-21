package com.example.post_service.service;

import com.example.post_service.entity.PostEntity;
import com.example.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public PostEntity createPost(PostEntity post) {
        return postRepository.save(post);
    }

    public PostEntity getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }

    public PostEntity updatePost(Long id, PostEntity newPost) {
        PostEntity existing = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        existing.setContent(newPost.getContent());
        existing.setUserId(newPost.getUserId());

        return postRepository.save(existing);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Unable to delete: post with ID " + id + " doesn't exists");
        }
        postRepository.deleteById(id);
    }
}
