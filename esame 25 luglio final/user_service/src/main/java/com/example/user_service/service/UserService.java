package com.example.user_service.service;

import com.example.user_service.DTO.UserDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;


    public List<UserDTO> getAll() {
        return repo.findAll().stream()
                .map(u -> new UserDTO(u.getId(), u.getName()))
                .collect(Collectors.toList());
    }

    public UserEntity save(UserEntity user) {
        return repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
