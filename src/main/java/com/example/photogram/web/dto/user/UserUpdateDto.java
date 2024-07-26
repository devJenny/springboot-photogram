package com.example.photogram.web.dto.user;

import com.example.photogram.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name; // 필수
    @NotBlank
    private String password; // 필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 선택값을 entity로 하는 건 위험. 추후 코드 수정 필요
    public User toEntity() {
        return User.builder()
                .name(name) // 이름을 기재 안 하면 문제. validation 체크
                .password(password) // pw를 기재 안 하면 문제. validation 체크
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
