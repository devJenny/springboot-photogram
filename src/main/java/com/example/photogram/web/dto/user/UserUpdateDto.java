package com.example.photogram.web.dto.user;

import com.example.photogram.domain.entity.User;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String name; // 필수
    private String password; // 필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 선택값을 entity로 하는 건 위험. 추후 코드 수정 필요
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
