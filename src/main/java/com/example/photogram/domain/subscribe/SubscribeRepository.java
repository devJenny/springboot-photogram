package com.example.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createdDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId", nativeQuery = true)
    void mUnSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

    @Query(value = "select count(*) from subscribe s where fromUserId =:principalId and toUserId =:pageUserId;", nativeQuery = true)
    int mSubscribeState(@Param("principalId") int principalId, @Param("pageUserId") int pageUserId);

    @Query(value = "select count(*) from subscribe s where fromUserId =:pageUserId", nativeQuery = true)
    int mSubscribeCount(@Param("pageUserId") int pageUserId);

}
