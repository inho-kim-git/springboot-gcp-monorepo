package com.ino.api.service.common;

import com.google.cloud.pubsub.v1.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2022/05/23
 * @Author inho.kim
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GooglePubSubService {

    private final Publisher testTopicPublisher;

    public void publish() {


    }

}
