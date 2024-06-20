package com.example.photogram.domain.repository;

import com.example.photogram.domain.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now()", nativeQuery=true)
    void mSubscribe(int fromUserId, int toUserId);

    @Query(value="DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId", nativeQuery=true)
    void mUnSubscribe(int fromUserId, int toUserId);

}
