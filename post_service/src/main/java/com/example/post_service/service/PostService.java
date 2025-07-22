package com.example.post_service.service;

import com.example.post_service.entity.PostEntity;
import com.example.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

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
        PostEntity existing = getPostById(id);
        existing.setContent(newPost.getContent());
        existing.setUserId(newPost.getUserId());
        return postRepository.save(existing);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }
}
