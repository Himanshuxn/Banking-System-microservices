package com.personal.bankmanagementsystem.dto;

import com.personal.bankmanagementsystem.model.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
