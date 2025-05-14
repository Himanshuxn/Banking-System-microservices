package com.personal.bankmanagementsystem.service.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.personal.bankmanagementsystem.model.TransactionType;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime timestamp;
}
