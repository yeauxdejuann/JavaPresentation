package com.example.SongStreamAPI;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String artist;

    @Column(nullable = false, length = 100)
    private String songName;

    @Column(nullable = false, length = 100)
    private String song;

    @Column(nullable = false, length = 500)
    private String genre;

    @Column(nullable = false, length = 500)
    private String album;

    @Column(nullable = false)
    private Integer releaseYear;


    public Song() {
    }

    public Song( String artist, String song, String album, String songName, String genre, Integer releaseYear) {
       this.artist = artist;
       this.song = song;
       this.songName = songName;
       this.album = album;
       this.genre = genre;
       this.releaseYear = releaseYear;
        
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSongName() { return songName; }
    public void setSongName(String name) { this.songName = name; }
    public String getGenre() { return genre; }
    public void setGenre(String description) { this.genre = description; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }
     public String getSong() { return song; }
    public void setSong(String song) { this.song = song; }
     public Integer getReleaseYear() { return releaseYear; }
     public Integer setReleaseYear(Integer releaseYear) {  return this.releaseYear = releaseYear; }
    
    @Override
    public String toString() {
        return
        
        "Song{" +

               "id=" + id + '\'' +
               ", artist='" + artist + '\'' +
               ", song=" + song + '\'' +
               ", songName='" + songName + '\'' +
               ", album=" + album + '\'' +
                ", genre=" + genre + '\'' +
                ", releaseYear=" + releaseYear +
        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song product = (Song) o;
        return Objects.equals(id, product.id) &&
               Objects.equals(songName, product.songName) &&
               Objects.equals(genre, product.genre) &&
               Objects.equals(artist, product.artist) &&
               Objects.equals(album, product.album) &&
               Objects.equals(releaseYear, product.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist, album, songName, genre, releaseYear);
    }
}