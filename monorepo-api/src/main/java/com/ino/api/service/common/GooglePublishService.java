package com.ino.api.service.common;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description Message Publish Service
 *
 * @Date 2022/05/23
 * @Author inho.kim
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GooglePublishService {

    private Publisher testTopicPublisher;

    @PostConstruct
    public void init() throws IOException {

        ProjectTopicName testTopic = ProjectTopicName.newBuilder()
                .setProject("docsflow-2")
                .setTopic("testTopic")
                .build();

        testTopicPublisher = Publisher.newBuilder(testTopic).build();
    }

    public void publish() {

        try {

            List<String> messages = Arrays.asList("first message", "second message");
            for (final String message : messages) {
                ByteString data = ByteString.copyFromUtf8(message);
                PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                        .setData(data)
                        .putAttributes("test", "test")
                        .build();

                // Once published, returns a server-assigned message id (unique within the topic)
                ApiFuture<String> future = testTopicPublisher.publish(pubsubMessage);

                // Add an asynchronous callback to handle success / failure
                ApiFutures.addCallback(
                        future,
                        new ApiFutureCallback<String>() {

                            @Override
                            public void onFailure(Throwable throwable) {
                                if (throwable instanceof ApiException) {
                                    ApiException apiException = ((ApiException) throwable);
                                    // details on the API exception
                                    System.out.println(apiException.getStatusCode().getCode());
                                    System.out.println(apiException.isRetryable());
                                }
                                System.out.println("Error publishing message : " + message);
                            }

                            @Override
                            public void onSuccess(String messageId) {
                                // Once published, returns server-assigned message ids (unique within the topic)
                                System.out.println("Success Callback: Published message " + messageId);
                            }
                        },
                        MoreExecutors.directExecutor());
            }
        } finally {
            if (testTopicPublisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                testTopicPublisher.shutdown();
            }
        }
    }
}
