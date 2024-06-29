package com.example.photogram.web.dto.Image;

import com.example.photogram.domain.entity.Image;
import com.example.photogram.domain.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(null)
                .build();
    }
}
