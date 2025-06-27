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
        if (songService.getAllSongs().isEmpty()) {
            System.out.println("Loading initial song data...");

            songService.addSong(new Song("Kendrick Lamar", "pool full of liquor, dive in it", "Hip Hop","Swimming Pools", "Hip-Hop",  2012));
            songService.addSong(new Song("Kendrick Lamar", "Alright", "Hip Hop","Alright", "Hip-Hop",  2015));
            songService.addSong(new Song("Kendrick Lamar", "Be HUMBLE, SIT DOWN.", "Hip Hop","HUMBLE.", "Hip-Hop",  2013));
            songService.addSong(new Song("Tems", "ride the wave of mi history...", "TEMS", "found","AFro-Beats", 2020  ));          
            System.out.println("Initial product data loaded.");
        } else {
            System.out.println("Products already exist, skipping initial data load.");
        }
    }
}