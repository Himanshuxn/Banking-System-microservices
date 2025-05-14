package com.personal.bankmanagementsystem.dto;

import com.personal.bankmanagementsystem.model.AccountStatus;
import com.personal.bankmanagementsystem.model.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {

    private Long id;
    private Long customerId;
    private AccountType accountType;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private AccountStatus status;
}
