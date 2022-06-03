package com.ino.api.service.common;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description Google PubSub Message Receiver
 * @Date 2022/06/02
 * @Author inho.kim
 */
@Component
@Slf4j
public class GoogleMessageReceiver implements MessageReceiver {

    @Override
    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
        log.info("message received : " + message.toString());
        consumer.ack();
    }
}
