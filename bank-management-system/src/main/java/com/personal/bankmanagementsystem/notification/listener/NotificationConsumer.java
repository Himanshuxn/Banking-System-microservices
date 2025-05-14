package com.personal.bankmanagementsystem.notification.listener;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.personal.bankmanagementsystem.NotificationService.EmailService;
import com.personal.bankmanagementsystem.service.event.NotificationEvent;

@Service
public class NotificationConsumer {
	private final EmailService emailService;
	public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(NotificationEvent event) {
        System.out.println("Sending notification to account: " + event.getAccountId());
		// Send Email
        emailService.sendEmail(event.getEmail(), "Transaction Alert", event.getMessage());

    }
}