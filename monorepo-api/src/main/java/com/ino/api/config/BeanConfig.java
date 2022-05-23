package com.ino.api.config;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.ProjectTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Description
 * @Date 2022/05/23
 * @Author inho.kim
 */
@Configuration
public class BeanConfig {

    @Bean("testTopicPublisher")
    public Publisher registerTestTopicPublisher() throws IOException {

        ProjectTopicName testTopic = ProjectTopicName.newBuilder()
                .setProject("docsflow-2")
                .setTopic("testTopic")
                .build();

        return Publisher.newBuilder(testTopic).build();
    }

}
