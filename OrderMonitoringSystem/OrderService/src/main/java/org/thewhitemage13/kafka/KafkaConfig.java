package org.thewhitemage13.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic createTopic1() {
        return TopicBuilder
                .name("order.created")
                .partitions(3)
                .replicas(1)
                .build();
    };

    @Bean
    NewTopic createTopic2() {
        return TopicBuilder
                .name("order.updated")
                .partitions(3)
                .replicas(1)
                .build();
    };

}
