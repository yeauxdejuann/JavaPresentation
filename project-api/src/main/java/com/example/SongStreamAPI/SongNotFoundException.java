package com.example.SongStreamAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // This annotation tells Spring to return 404 when this exception is thrown
public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException(Long id) {
        super("Could not find song " + id);
    }
}
