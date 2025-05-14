package com.personal.bankmanagementsystem.service;

import com.personal.bankmanagementsystem.model.Customer;
import com.personal.bankmanagementsystem.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer save(Customer customer) {
        Customer saved = repo.save(customer);
        log.info("Saved customer with ID: {}", saved.getId());
        return saved;
    }

    public List<Customer> findAll() {
        List<Customer> all = repo.findAll();
        log.info("Retrieved {} customers", all.size());
        return all;
    }

    public Customer findById(Long id) {
        Customer customer = repo.findById(id).orElse(null);
        if (customer == null) {
            log.warn("Customer with ID {} not found", id);
        } else {
            log.info("Fetched customer with ID: {}", id);
        }
        return customer;
    }

    public void delete(Long id) {
        repo.deleteById(id);
        log.info("Deleted customer with ID: {}", id);
    }
}