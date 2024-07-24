package com.example.photogram.domain.repository;

import com.example.photogram.domain.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image where userId in (select toUserid from subscribe s where fromUserId = :principleId) ORDER BY ID DESC", nativeQuery = true)
    Page<Image> mStory(int principleId, Pageable pageable);

}
