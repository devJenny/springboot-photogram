package com.example.photogram.domain.repository;

import com.example.photogram.domain.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(value="INSERT INTO subscribe(fromUserId, toUserId, createdDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery=true)
    void mSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);


    @Modifying
    @Query(value="DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId", nativeQuery=true)
    void mUnSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

}
