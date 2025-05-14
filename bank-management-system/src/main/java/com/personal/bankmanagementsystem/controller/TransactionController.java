package com.personal.bankmanagementsystem.controller;

import com.personal.bankmanagementsystem.dto.TransactionRequestDTO;
import com.personal.bankmanagementsystem.dto.TransactionResponseDTO;
import com.personal.bankmanagementsystem.model.TransactionType;
import com.personal.bankmanagementsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> performTransaction(@RequestBody TransactionRequestDTO request) {
        TransactionResponseDTO createdTransaction = transactionService.performTransaction(request);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountId));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/type/{type}")
    public List<TransactionResponseDTO> getByType(@PathVariable TransactionType type) {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/date-range")
    public List<TransactionResponseDTO> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return transactionService.getTransactionsByDateRange(start, end);
    }

}
