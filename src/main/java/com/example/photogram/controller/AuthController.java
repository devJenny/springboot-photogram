package com.example.photogram.controller;

import com.example.photogram.domain.entity.User;
import com.example.photogram.dto.auth.SignupDto;
import com.example.photogram.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {
        log.info("signupDto: {}", signupDto.toString());
        // User <- SignupDto
        User user = signupDto.toEntity();
        log.info("user: {}", user.toString());

        User userEntity = authService.join(user);
        log.info("userEntity: {}", userEntity.toString());
        return "auth/signin";
    }

}
