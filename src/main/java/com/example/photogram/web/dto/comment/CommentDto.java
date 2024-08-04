package com.example.photogram.web.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// NotNull = NUll값 체크
// NotEmpty = 빈값이거나 null 체크
// NotBlank = 빈값이거나 null 체크 그리고 빈 공백까지

@Data
public class CommentDto {

    @NotBlank
    private String content;
    @NotNull
    private int imageId;

    // toEntity가 필요없다
}
