package com.personal.bankmanagementsystem.service;

import com.personal.bankmanagementsystem.dto.TransactionRequestDTO;
import com.personal.bankmanagementsystem.dto.TransactionResponseDTO;
import com.personal.bankmanagementsystem.model.Account;
import com.personal.bankmanagementsystem.model.Transaction;
import com.personal.bankmanagementsystem.model.TransactionType;
import com.personal.bankmanagementsystem.repository.TransactionRepository;
import com.personal.bankmanagementsystem.service.client.AccountClient;
import com.personal.bankmanagementsystem.service.event.NotificationEvent;
import com.personal.bankmanagementsystem.service.event.NotificationProducer;
import com.personal.bankmanagementsystem.service.event.TransactionEventPublisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;
    private final TransactionEventPublisher eventPublisher;
    private final NotificationProducer notification;


    @Transactional
    public TransactionResponseDTO performTransaction(TransactionRequestDTO request) {
        Account fromAccount = accountClient.getAccountById(request.getFromAccountId());
     System.out.println(fromAccount);
        Account toAccount = null;
        if (request.getTransactionType() == TransactionType.TRANSFER) {
            toAccount = accountClient.getAccountById(request.getToAccountId());
        }
        System.out.println(toAccount);
        switch (request.getTransactionType()) {
            case DEPOSIT -> {
                fromAccount.setBalance(fromAccount.getBalance().add(request.getAmount()));
                accountClient.updateAccount(fromAccount); // Remote update
            }
            case WITHDRAWAL -> {
                if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
                    throw new RuntimeException("Insufficient balance");
                }
                fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
                accountClient.updateAccount(fromAccount);
            }
            case TRANSFER -> {
                if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
                    throw new RuntimeException("Insufficient balance");
                }
                fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
                toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
                accountClient.updateAccount(fromAccount);
                accountClient.updateAccount(toAccount);
            }
        }

        Transaction transaction = Transaction.builder()
                .fromAccountId(request.getFromAccountId())
                .toAccountId(request.getToAccountId())
                .amount(request.getAmount())
                .transactionType(request.getTransactionType())
                .timestamp(LocalDateTime.now())
                .build();

        Transaction saved = transactionRepository.save(transaction);
        eventPublisher.publishTransactionEvent(saved);
        notification.sendNotification(NotificationEvent.builder()
                .accountId(request.getFromAccountId())
                .message("Transaction of " + request.getAmount() + " was successful.")
                .amount(request.getAmount())
                .timestamp(LocalDateTime.now())
                .email("hs5432115@gmail.com")
                .build());
        return mapToResponseDTO(saved);
    }

    public List<TransactionResponseDTO> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> getTransactionsByType(TransactionType type) {
        return transactionRepository.findByTransactionType(type).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionResponseDTO> getTransactionsByDateRange(LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByTimestampBetween(start, end).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO mapToResponseDTO(Transaction tx) {
        return TransactionResponseDTO.builder()
                .id(tx.getId())
                .fromAccountId(tx.getFromAccountId())
                .toAccountId(tx.getToAccountId())
                .amount(tx.getAmount())
                .transactionType(tx.getTransactionType())
                .timestamp(tx.getTimestamp())
                .build();
    }
    
}
