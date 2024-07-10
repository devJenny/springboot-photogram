package com.example.photogram.controller;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.entity.User;
import com.example.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable("id") int id, Model model) {
        User userEntity = userService.userProfile(id);
        model.addAttribute("user", userEntity);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable("id") int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 추천
//        System.out.println("세션 정보: " + principalDetails.getUser());

        // ㄴㄴ
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
//        System.out.println("직접 찾은 세션 정보: " + mPrincipalDetails.getUser());

        return "user/update";
    }
}
