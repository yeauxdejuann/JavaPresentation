package com.example.SongStreamAPI.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class SongRequest {

    @NotBlank(message = "Artist name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String artist;

    @Size(max = message = "Description cannot exceed 500 characters")
    private String songName;

     @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String album;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private String genre;

       @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private String releaseYear;




    public SongRequest() {
    }

    public SongRequest( String artist, String album, String songName, String genre, Integer releaseYear) {
        this.artist = artist;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               '}';
    }
}