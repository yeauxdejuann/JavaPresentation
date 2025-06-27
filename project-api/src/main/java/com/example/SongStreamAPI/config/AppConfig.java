package com.example.productapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Mark as a Spring component to be managed by the Spring context
public class AppConfig implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    // Inject custom properties using @Value
    @Value("${app.message}") // Property name from application.yml
    private String appMessage;

    @Value("${app.api-version}")
    private String apiVersion;

    @Override
    public void run(String... args) throws Exception {
        // This method runs once the application context has loaded
        logger.info("Application Configuration Loaded:");
        logger.info("  App Message: {}", appMessage);
        logger.info("  API Version: {}", apiVersion);
    }
}