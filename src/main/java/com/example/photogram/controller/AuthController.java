package com.example.photogram.controller;

import com.example.photogram.domain.user.User;
import com.example.photogram.web.dto.auth.SignupDto;
import com.example.photogram.handler.ex.CustomValidationException;
import com.example.photogram.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

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
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

            User user = signupDto.toEntity();
            log.info("user: {}", user.toString());

            User userEntity = authService.join(user);
            log.info("userEntity: {}", userEntity.toString());
            return "auth/signin";
    }
}
