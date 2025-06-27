package com.example.productapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;

    public DataLoader(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productService.getAllProducts().isEmpty()) {
            System.out.println("Loading initial product data...");

            productService.addProduct(new Song("Laptop Pro", "Powerful laptop for professionals", new BigDecimal("1200.00")));
            productService.addProduct(new Song("Wireless Mouse", "Ergonomic wireless mouse", new BigDecimal("25.99")));
            productService.addProduct(new Song("Mechanical Keyboard", "RGB mechanical gaming keyboard", new BigDecimal("89.95")));

            System.out.println("Initial product data loaded.");
        } else {
            System.out.println("Products already exist, skipping initial data load.");
        }
    }
}