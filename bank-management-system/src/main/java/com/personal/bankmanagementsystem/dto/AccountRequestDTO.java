package com.personal.bankmanagementsystem.dto;

import com.personal.bankmanagementsystem.model.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Initial balance is required")
    private BigDecimal balance;
    private BigDecimal initialDeposit;
}
