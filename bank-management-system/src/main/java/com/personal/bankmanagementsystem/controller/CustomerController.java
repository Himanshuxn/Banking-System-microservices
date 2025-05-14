package com.personal.bankmanagementsystem.controller;

import com.personal.bankmanagementsystem.dto.CustomerRequestDTO;
import com.personal.bankmanagementsystem.dto.CustomerResponseDTO;
import com.personal.bankmanagementsystem.model.Customer;
import com.personal.bankmanagementsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        
        Customer saved = service.save(customer);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return service.findAll().stream()
                .map(CustomerResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = service.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CustomerResponseDTO(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
