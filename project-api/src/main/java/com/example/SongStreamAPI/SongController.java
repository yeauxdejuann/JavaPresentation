
package com.example.SongStreamAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SongStreamAPI.SongService;
import com.example.SongStreamAPI.dto.SongRequest;
import com.example.SongStreamAPI.dto.SongResponse;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
// import java.util.stream.Collectors;
import org.slf4j.Logger; // NEW
import org.slf4j.LoggerFactory; // NEW

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private static final Logger logger = LoggerFactory.getLogger(SongController.class); // NEW

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    private SongResponse convertToResponseDto(Song song) {
        if (song == null) {
            return null;
        } //String artist, String album, String genre, Integer releaseYear
        return new SongResponse( song.getId(), song.getArtist(), song.getSong(), song.getAlbum(), song.getSongName(), song.getGenre(), song.getReleaseYear() );
    }

    private Song convertToEntity(SongRequest songRequest) {
        if (songRequest == null) {
            return null;
        }
        return new Song(songRequest.getArtist(), songRequest.getAlbum(), songRequest.getSong(), songRequest.getSongName(), songRequest.getGenre(), songRequest.getReleaseYear());
    }

    @GetMapping
    public Page<SongResponse> getAllSongsPage(@PageableDefault(size = 5, sort = "songName") Pageable pageable) {
        return songService.getAllSongs(pageable)
                             .map(this::convertToResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable Long id) {
        Song product = songService.getSongById(id);
        return new ResponseEntity<>(convertToResponseDto(product), HttpStatus.OK);
    }

   // MODIFIED: Call asynchronous method after product creation
    @PostMapping
    public ResponseEntity<SongResponse> addSong(@Valid @RequestBody SongRequest songRequest) {
        logger.info("Controller: Received request to add song: {}", songRequest.getSong()); // NEW
        Song songToSave = convertToEntity(songRequest);
        Song newSong = songService.addSong(songToSave);

        // Trigger the asynchronous background process
        songService.processAfterSongCreation(newSong.getId(), newSong.getSongName()); // NEW

        logger.info("Controller: Responding for song {} (Async task initiated).", newSong.getSongName()); // NEW
        return new ResponseEntity<>(convertToResponseDto(newSong), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SongResponse> updateSong(@PathVariable Long id, @Valid @RequestBody SongRequest songRequest) {
        Song updatedEntityDetails = convertToEntity(songRequest);
        Song updatedProduct = songService.updateSong(id, songRequest); // <-- CORRECTED LINE
        return new ResponseEntity<>(convertToResponseDto(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        if (songService.deleteSong(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
