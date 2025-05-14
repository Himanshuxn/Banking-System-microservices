package com.personal.bankmanagementsystem.frauddetection.listener;

import com.personal.bankmanagementsystem.model.Transaction;
import com.personal.bankmanagementsystem.frauddetection.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionEventListener {

    private final FraudDetectionService fraudDetectionService;

    @KafkaListener(topics = "transactions", groupId = "fraud-group")
    public void handleTransactionEvent(Transaction transaction) {
        fraudDetectionService.analyzeTransaction(transaction);
    }
}
