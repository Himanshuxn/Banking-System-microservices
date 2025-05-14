package com.personal.bankmanagementsystem.controller;

import com.personal.bankmanagementsystem.dto.AccountRequestDTO;
import com.personal.bankmanagementsystem.dto.AccountResponseDTO;
import com.personal.bankmanagementsystem.model.Account;
import com.personal.bankmanagementsystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 🔸 Create Account
    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO requestDTO) {
        return ResponseEntity.ok(accountService.createAccount(requestDTO));
    }

    // 🔸 Get All Accounts
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // 🔸 Get Account By ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // 🔸 Delete Account
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account updated = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updated);
    }

}
