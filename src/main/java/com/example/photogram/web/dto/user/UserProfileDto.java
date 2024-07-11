package com.example.photogram.web.dto.user;

import com.example.photogram.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
    private Boolean pageOwnerState;
    private int imageCount;
    private User user;
}
