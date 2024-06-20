package com.example.photogram.service;

import com.example.photogram.domain.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void subscribe(int fromUserId, int toUserid) {
        subscribeRepository.mSubscribe(fromUserId, toUserid);
    }

    @Transactional
    public void unSubscribe(int fromUserId, int toUserid) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserid);

    }

}
