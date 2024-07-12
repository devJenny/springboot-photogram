package com.example.photogram.service;

import com.example.photogram.domain.repository.SubscribeRepository;
import com.example.photogram.handler.ex.CustomApiException;
import com.example.photogram.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;


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

    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(int principalId, int pageUserId) {
        return null;
    }
}
