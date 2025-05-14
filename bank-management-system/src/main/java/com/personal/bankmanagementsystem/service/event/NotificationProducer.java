package com.personal.bankmanagementsystem.service.event;

import com.personal.bankmanagementsystem.service.event.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void sendNotification(NotificationEvent event) {
        kafkaTemplate.send("notification-topic", event);
    }
}
