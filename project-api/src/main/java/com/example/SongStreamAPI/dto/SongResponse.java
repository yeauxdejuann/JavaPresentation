package com.example.SongStreamAPI.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;

public class SongResponse {
    private Long id;
    private String artist;

    private String songName;

    private String song;

    private String genre;

    private String album;


    private Integer releaseYear;

    public SongResponse() {
    }

   public SongResponse( Long id, String artist, String album, String song, String songName, String genre, Integer releaseYear) {
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
        
        "SongResponse{" +
                ", id='" + id + '\'' +
               ", artist='" + artist + '\'' +
               ", song=" + song + '\'' +
               ", songName='" + songName + '\'' +
               ", album=" + album + '\'' +
                ", genre=" + genre + '\'' +
                ", releaseYear=" + releaseYear +
        '}';
    }
}