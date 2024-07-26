package com.example.photogram.web.api;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.image.Image;
import com.example.photogram.service.ImageService;
import com.example.photogram.service.LikesService;
import com.example.photogram.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PageableDefault(size = 3) Pageable pageable) {
        Page<Image> images = imageService.imageStory(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMResDto<>(1, "성공", images), HttpStatus.OK);
    }

    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable("imageId") int imageId
            , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.likes(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto(1, "좋아요 성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable("imageId") int imageId
            , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.unLikes(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto(1, "좋아요 취소 성공", null), HttpStatus.OK);
    }
}