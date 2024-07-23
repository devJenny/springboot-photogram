package com.example.photogram.domain.repository;

import com.example.photogram.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image where userId in (select toUserid from subscribe s where fromUserId = :principleId);", nativeQuery = true)
    List<Image> mStory(int principleId);

}
