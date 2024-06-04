package com.example.photogram.service;

import com.example.photogram.domain.entity.User;
import com.example.photogram.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public User join(User user) {
        // 회원가입 진행
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
