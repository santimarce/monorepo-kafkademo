package com.microkafka.notifications.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClaimNotificationConsumer {
    @KafkaListener(topics = "claim-topic", groupId = "notificaciones-group")
    public void consume(String message) {
        System.out.println(message);
    }
}
