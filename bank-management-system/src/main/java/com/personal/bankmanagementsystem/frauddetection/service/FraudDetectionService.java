package com.personal.bankmanagementsystem.frauddetection.service;

import com.personal.bankmanagementsystem.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionService {

    public void analyzeTransaction(Transaction transaction) {
        // Example rule-based check
        if (transaction.getAmount().doubleValue() > 1000) {
            System.out.println("⚠️ Potential Fraudulent Transaction Detected: " + transaction);
            // Optionally publish alert or notify
        } else {
            System.out.println("✅ Transaction looks normal: " + transaction);
        }

        // 🔮 Later: Call ML model here (TensorFlow, Weka, etc.)
    }
}
