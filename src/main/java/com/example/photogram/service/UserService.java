package com.example.photogram.service;

import com.example.photogram.domain.entity.User;
import com.example.photogram.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User userUpdate(int id, User user) {

        // 1. 영속화
        User userEntity = userRepository.findById(id).get(); // optional -> 1. 무조건 찾음 get() 2. 못찾음 예외처리 orElseThrow()

        // 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encodedPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }
}
