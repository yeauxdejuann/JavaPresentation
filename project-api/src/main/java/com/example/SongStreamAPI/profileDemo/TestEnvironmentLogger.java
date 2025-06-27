package com.example.SongStreamAPI.profileDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Profile("test") // This bean will only be active when the 'test' profile is active
public class TestEnvironmentLogger {

    private static final Logger logger = LoggerFactory.getLogger(TestEnvironmentLogger.class);

    @PostConstruct
    public void init() {
        logger.info("TEST Profile Active: Logging for test environment setup.");
        // In a real app, this might set up test-specific configurations or mocks.
    }
}