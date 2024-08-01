package org.thewhitemage13.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thewhitemage13.OrderCreatedEvent;

@Component
@KafkaListener(topics = "order.created")
public class OrderCreateEvent {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderCreateEvent.class);

    @KafkaHandler
    public void create(OrderCreatedEvent orderCreatedEvent) {
        LOGGER.info("Order created: {}", orderCreatedEvent.toString());
    }
}
