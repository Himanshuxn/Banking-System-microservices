package com.personal.bankmanagementsystem.service.client;

import com.personal.bankmanagementsystem.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final RestTemplate restTemplate;

    @Value("${account.service.url}")
    private String accountServiceUrl;

    public Account getAccountById(Long id) {
        URI uri = URI.create(accountServiceUrl + "/api/accounts/" + id);
        ResponseEntity<Account> response = restTemplate.getForEntity(uri, Account.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch account with id: " + id);
        }
    }

    public void updateAccount(Account account) {
        URI uri = URI.create(accountServiceUrl + "/api/accounts/" + account.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> request = new HttpEntity<>(account, headers);

        ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to update account with id: " + account.getId());
        }
    }
}
