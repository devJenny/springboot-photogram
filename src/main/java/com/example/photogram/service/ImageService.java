package com.example.photogram.service;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.image.Image;
import com.example.photogram.domain.image.ImageRepository;
import com.example.photogram.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void upload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();// 1.jpg
        log.info("imageFileName: {}", imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);


        // 통신, I/O -> 예외 발생
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);

//        log.info("imageEntity: {}", imageEntity);
    }

    @Transactional(readOnly = true) // 영속성 컨텍스트를 변경 감지해서, 더티체킹. flush(반영)
    public Page<Image> imageStory(int principleId, Pageable pageable) {
        Page<Image> images = imageRepository.mStory(principleId, pageable);

        // 2(cos)로그인
        // images에 좋아요 상태 담기
        images.forEach((image) -> {
            image.getLikes().forEach((like)-> {
                if (like.getUser().getId() == principleId) { // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로긴한 사람이 좋아요 한것인지 비교
                    image.setLikeState(true);
                }
            });

        });


        return images;
    }

}
