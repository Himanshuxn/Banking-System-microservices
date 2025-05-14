package com.personal.bankmanagementsystem.service.event;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEvent {
    private Long accountId;
    private String message;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String email;
}
