package com.example.SongStreamAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // NEW


@SpringBootApplication
@EnableScheduling // NEW: Enables Spring's scheduling capabilities
public class SongAPI {

    public static void main(String[] args) {
        SpringApplication.run(SongAPI.class, args);
    }

}