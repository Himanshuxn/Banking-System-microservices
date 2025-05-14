package com.personal.bankmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId; // Foreign key reference to Customer

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
