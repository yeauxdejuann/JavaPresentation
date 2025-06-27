package com.example.SongStreamAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SongStreamAPI.SongNotFoundException;
import com.example.SongStreamAPI.SongRepository;
import com.example.SongStreamAPI.Song;
import com.example.SongStreamAPI.dto.SongRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Service
public class SongService {
    private static final Logger logger = LoggerFactory.getLogger(SongService.class); // NEW

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRespository) {
        this.songRepository = songRespository;
    }

    public Page<Song> getAllSongs(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
        .orElseThrow(() -> new SongNotFoundException(id));
    }

    @Transactional
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    @Transactional
    public Song updateSong(Long id, SongRequest songRequest) {
        Song existingProduct = songRepository.findById(id)
        .orElseThrow(() -> new SongNotFoundException(id));

        existingProduct.setSongName(songRequest.getSongName());
        existingProduct.setGenre(songRequest.getGenre());
        return songRepository.save(existingProduct);
    }

    @Transactional
    public boolean deleteSong(Long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true;
        }
        return false;
    }

//     // Assisted by watsonx Code Assistant

// public Song processSongFile(File songFile) {
//     try (InputStream inputStream = new FileInputStream(songFile)) {
//         // Use the stream API to process the song file
//         return processSongStream(inputStream);
//     } catch (IOException e) {
//         // Handle the exception appropriately
//         throw new RuntimeException("Error processing song file", e);
//     }
// }

// // Assisted by watsonx Code Assistant

// private Song processSongStream(InputStream songInputStream) {
//     // Use Java 8 Stream API to process the song file contents
//     // For example, to read and count lines (assuming each line represents a metadata field):
//     return StreamSupport.stream(new Song() {
//         @Override
//         public boolean tryAdvance(Consumer<? super String> action) {
//             // Read a line from the input stream and pass it to the action
//             // You may need to implement a custom Spliterator to read from the InputStream
//         }

//         // @Override
//         // public Spliterator.OfObject<String> trySplit() {
//         //     return null;
//         // }
//     }, 32, Long::sum, more())
//             .map(this::processLine) // Replace with your actual processing logic
//             .findAny() // Get the first processed line (adjust as needed)
//             .orElse(null); // Return null if no lines were processed
// }



    // NEW: Asynchronous method
    @Async // This method will be executed in a separate thread
    public void processAfterSongCreation(Long songId, String songName) {
        logger.info("Async Task Started: Processing song ID {} ({}) in thread {}",
                    songId, songName, Thread.currentThread().getName());
        try {
            // Simulate a long-running operation, e.g., sending an email,
            // calling another microservice, generating a report, etc.
            Thread.sleep(5000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            logger.error("Async task interrupted for song ID {}: {}", songId, e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
        logger.info("Async Task Finished: Processing song ID {} ({}) completed.", songId, songName);
    }
}