package com.personal.bankmanagementsystem.dto;

import com.personal.bankmanagementsystem.model.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequestDTO {
    private Long fromAccountId;
    private Long toAccountId;
    private TransactionType transactionType; // DEPOSIT or WITHDRAWAL
    private BigDecimal amount;
}
