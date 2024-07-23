package com.example.photogram.service;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.entity.Image;
import com.example.photogram.domain.repository.ImageRepository;
import com.example.photogram.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    public List<Image> imageStory(int principleId) {
        List<Image> images = imageRepository.mStory(principleId);
        return images;
    }

}
