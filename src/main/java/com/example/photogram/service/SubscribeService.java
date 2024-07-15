package com.example.photogram.service;

import com.example.photogram.domain.repository.SubscribeRepository;
import com.example.photogram.handler.ex.CustomApiException;
import com.example.photogram.web.dto.subscribe.SubscribeDto;
import jakarta.persistence.EntityManager;

import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em; // Repository는 EntityManager를 구현해서 만들어져 있는 구현체

    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(int principalId, int pageUserId) {

        // 쿼리 준비
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("if ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId=u.id),1,0) subscribeState, ");
        sb.append("if (( ? =u.id),1,0) as equalUserState ");
        sb.append("FROM users u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?"); // 세미콜론 첨부하면 안됨

        // 1. 물음표 principalId
        // 2. 물음표 principalId
        // 3. 물음표 pageUserId

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 쿼리 실행 (qlrm 라이브러리 필요 = DTO에 DB 결과를 매핑하기 위해서
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }

    @Transactional
    public void subscribe(int fromUserId, int toUserid) {

        try {
            subscribeRepository.mSubscribe(fromUserId, toUserid);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void unSubscribe(int fromUserId, int toUserid) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserid);
    }


}
