package com.example.photogram.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image where userId in (select toUserid from subscribe s where fromUserId = :principleId) ORDER BY ID DESC", nativeQuery = true)
    Page<Image> mStory(@Param("principleId") int principleId, Pageable pageable);

    @Query(value = "SELECT i.* FROM image i INNER JOIN (select imageId, count(imageId) likeCount from likes l group by imageId) c on i.id = c.imageId ORDER BY likeCount DESC", nativeQuery = true)
    List<Image> mPopular();
}
