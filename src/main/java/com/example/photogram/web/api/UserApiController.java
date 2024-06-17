package com.example.photogram.web.api;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.entity.User;
import com.example.photogram.service.UserService;
import com.example.photogram.web.dto.CMResDto;
import com.example.photogram.web.dto.user.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMResDto<?> update(@PathVariable("id") int id, @Valid UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User userEntity = userService.userUpdate(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity); // 세션 정보 변경
        return new CMResDto<>(1,"회원수정완료",userEntity);
    }
}
