package com.example.productapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // NEW


@SpringBootApplication
@EnableScheduling // NEW: Enables Spring's scheduling capabilities
public class ProductApiApp {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApp.class, args);
    }

}