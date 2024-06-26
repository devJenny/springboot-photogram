package com.example.photogram.domain.repository;

import com.example.photogram.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
     User findByUsername(String username);
}
