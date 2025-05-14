package com.personal.bankmanagementsystem.service;

import com.personal.bankmanagementsystem.dto.AccountRequestDTO;
import com.personal.bankmanagementsystem.dto.AccountResponseDTO;
import com.personal.bankmanagementsystem.model.AccountStatus;
import com.personal.bankmanagementsystem.model.Account;
import com.personal.bankmanagementsystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        Account account = Account.builder()
                .customerId(requestDTO.getCustomerId())
                .accountType(requestDTO.getAccountType())
                .balance(requestDTO.getInitialDeposit() != null ? requestDTO.getInitialDeposit() : BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .build();

        Account saved = accountRepository.save(account);

        return mapToResponseDTO(saved);
    }

    public List<AccountResponseDTO> getAccountsByCustomer(Long customerId) {
        return accountRepository.findByCustomerId(customerId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private AccountResponseDTO mapToResponseDTO(Account account) {
        return AccountResponseDTO.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .createdAt(account.getCreatedAt())
                .build();
    }

	public List<AccountResponseDTO> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
	}

	public AccountResponseDTO getAccountById(Long id) {
	    return accountRepository.findById(id)
	            .map(this::mapToResponseDTO)
	            .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
	}


	public void deleteAccount(Long id) {
	    if (!accountRepository.existsById(id)) {
	        throw new RuntimeException("Cannot delete. Account not found with id: " + id);
	    }
	    accountRepository.deleteById(id);
	}
	public Account updateAccount(Long id, Account account) {
	    Account existing = accountRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Account not found"));
	    existing.setBalance(account.getBalance());
	    existing.setStatus(account.getStatus());
	    // Set other fields if needed
	    return accountRepository.save(existing);
	}

}
