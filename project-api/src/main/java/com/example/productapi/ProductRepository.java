package com.example.productapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Spring Data JPA repository
public interface ProductRepository extends JpaRepository<Song, Long> {
    // Spring Data JPA automatically provides methods like:
    // findAll(), findById(id), save(entity), deleteById(id), count(), etc.
    // You don't need to write any implementation here!
}