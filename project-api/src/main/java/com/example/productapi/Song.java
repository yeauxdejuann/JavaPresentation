package com.example.productapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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

    @Column(length = 500)
    private String genre;

    @Column(length = 500)
    private String album;

    @Column(length = 500)
    private String releaseYear;


    public Song() {
    }

    public Song(String name, String description, BigDecimal price) {
        this.songName = name;
        this.genre = description;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSongName() { return songName; }
    public void setSongName(String name) { this.songName = name; }
    public String getGenre() { return genre; }
    public void setGenre(String description) { this.genre = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + songName + '\'' +
               ", description='" + genre + '\'' +
               ", price=" + price +
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
               Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songName, genre, price);
    }
}