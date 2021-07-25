package com.store.bubostore.repository;

import com.store.bubostore.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Integer> {
    // Custom Bird Service query
    // Search product by name
    @Query("SELECT bird FROM Bird bird WHERE bird.name LIKE %:name%")
    List<Bird> findByBirdName(String name);
}