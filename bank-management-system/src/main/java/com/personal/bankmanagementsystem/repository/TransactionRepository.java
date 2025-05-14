package com.personal.bankmanagementsystem.repository;

import com.personal.bankmanagementsystem.model.Transaction;
import com.personal.bankmanagementsystem.model.TransactionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	 // This retrieves all transactions where the account was either sender or receiver
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);
    
    List<Transaction> findByTransactionType(TransactionType type);

    List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

}
