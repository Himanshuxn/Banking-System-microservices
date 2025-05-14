package com.personal.bankmanagementsystem.service.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.personal.bankmanagementsystem.model.Transaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionEventPublisher {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public void publishTransactionEvent(Transaction transaction) {
        kafkaTemplate.send("transactions", transaction.getId().toString(), transaction);
        System.out.println("📤 Published transaction event to Kafka: " + transaction);
    }
}
