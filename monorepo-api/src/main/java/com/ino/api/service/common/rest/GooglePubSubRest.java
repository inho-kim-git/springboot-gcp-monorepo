package com.ino.api.service.common.rest;

import com.ino.api.service.common.GooglePublishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description PubSub Rest
 *
 * @Date 2022/05/24
 * @Author inho.kim
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class GooglePubSubRest {

    private final GooglePublishService googlePublishService;

    @PostMapping("/pub")
    public void publishTest() {
        googlePublishService.publish();
    }
}
