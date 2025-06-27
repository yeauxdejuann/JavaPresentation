package com.example.SongStreamAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final SongService songService;

    public DataLoader(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (songService.getAllProducts().isEmpty()) {
            System.out.println("Loading initial product data...");

            songService.addSong(new Song("Laptop Pro", "Powerful laptop for professionals", new BigDecimal("1200.00")));
            songService.addSong(new Song("Wireless Mouse", "Ergonomic wireless mouse", new BigDecimal("25.99")));
            songService.addSong(new Song("Mechanical Keyboard", "RGB mechanical gaming keyboard", new BigDecimal("89.95")));

            System.out.println("Initial product data loaded.");
        } else {
            System.out.println("Products already exist, skipping initial data load.");
        }
    }
}