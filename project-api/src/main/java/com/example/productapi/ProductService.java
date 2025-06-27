package com.example.productapi; // <-- CHANGED: Now directly in com.example.productapi

import com.example.productapi.Song;
import com.example.productapi.ProductRepository; // <-- CHANGED: No longer in 'repository' package
import com.example.productapi.ProductNotFoundException;
import com.example.productapi.dto.ProductRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class); // NEW

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Song> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Song> getAllProducts() {
        return productRepository.findAll();
    }

    public Song getProductById(Long id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public Song addProduct(Song product) {
        return productRepository.save(product);
    }

    @Transactional
    public Song updateProduct(Long id, ProductRequest productRequest) {
        Song existingProduct = productRepository.findById(id)
                                    .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setSongName(productRequest.getName());
        existingProduct.setGenre(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());

        return productRepository.save(existingProduct);
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // NEW: Asynchronous method
    @Async // This method will be executed in a separate thread
    public void processAfterProductCreation(Long productId, String productName) {
        logger.info("Async Task Started: Processing product ID {} ({}) in thread {}",
                    productId, productName, Thread.currentThread().getName());
        try {
            // Simulate a long-running operation, e.g., sending an email,
            // calling another microservice, generating a report, etc.
            Thread.sleep(5000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            logger.error("Async task interrupted for product ID {}: {}", productId, e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
        logger.info("Async Task Finished: Processing product ID {} ({}) completed.", productId, productName);
    }
}