package com.personal.bankmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementSystemApplication.class, args);
	}

    @Bean
    RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
