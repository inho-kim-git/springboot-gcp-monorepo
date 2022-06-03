package com.ino.api.config;

import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.ino.api.service.common.GoogleMessageReceiver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description Google Subscriber Config
 * @Date 2022/05/24
 * @Author inho.kim
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class GoogleSubscriberConfig {

    private final GoogleMessageReceiver googleMessageReceiver;
    private Subscriber subscriber;

    @PostConstruct
    public void init() {

        ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of("docsflow-2", "testTopic-sub");

        subscriber = Subscriber.newBuilder(subscriptionName, googleMessageReceiver).build();
        subscriber.startAsync().awaitRunning();
        log.info("Listening for messages on %s:\n", subscriptionName);
    }
}
