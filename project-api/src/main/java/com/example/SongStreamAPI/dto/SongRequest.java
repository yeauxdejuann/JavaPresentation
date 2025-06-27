package com.example.SongStreamAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SongRequest {

    private Long id;

    @NotBlank(message = "Artist name is required")
    @Size(min = 2, max = 100, message = "Artist name must be between 2 and 100 characters")
    private String artist;

    @NotNull(message = "Song name is required")
    @Size(message ="Song name cannot exceed 500 characters")
    private String songName;

    @NotNull(message = "Song is required")
    @Size(message ="Song cannot exceed 1500 characters")
    private String song;

    @Size(max = 50, message ="Album cannot exceed 500 characters")
    private String album;

    @Size(message = "Genre cannot exceed 50 characters")
    private String genre;

    private Integer releaseYear;




    public SongRequest() {
    }

    public SongRequest(Long id, String artist, String album, String song, String songName, String genre, Integer releaseYear) {
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.songName = songName;
        this.genre = genre;
        this.song = song;
        this.releaseYear = releaseYear;
        
    }

   
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
     public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;

    }
  
   
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }

    public Integer getReleaseYear() {    
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
  @Override
    public String toString() {
        return
        
        "SongResquest{" +

               ", artist='" + artist + '\'' +
               ", song=" + song + '\'' +
               ", songName='" + songName + '\'' +
               ", album=" + album + '\'' +
                ", genre=" + genre + '\'' +
                ", releaseYear=" + releaseYear +
        '}';
    }


}