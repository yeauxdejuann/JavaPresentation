package com.example.productapi.profileDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct; // For @PostConstruct

@Component
@Profile("dev") // This bean will only be active when the 'dev' profile is active
public class DevDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DevDataInitializer.class);

    @PostConstruct
    public void init() {
        logger.info("DEV Profile Active: Running development data initialization logic.");
        // In a real app, this might load dummy data for dev, or connect to a dev DB.
    }
}