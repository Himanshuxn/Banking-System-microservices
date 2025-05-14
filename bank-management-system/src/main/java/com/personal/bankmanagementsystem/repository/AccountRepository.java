package com.personal.bankmanagementsystem.repository;

import com.personal.bankmanagementsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    List<Account> findByCustomerId(Long customerId);
}
