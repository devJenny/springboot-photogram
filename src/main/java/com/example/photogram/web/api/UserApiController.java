package com.example.photogram.web.api;

import com.example.photogram.web.dto.user.UserUpdateDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PutMapping("/api/user/{id}")
    public String update(UserUpdateDto userUpdateDto) {
        System.out.println("userUpdateDto = " + userUpdateDto);
        return "ok";
    }
}