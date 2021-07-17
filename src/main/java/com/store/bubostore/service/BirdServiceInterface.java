package com.store.bubostore.service;

import com.store.bubostore.entity.Bird;

import java.util.Optional;

public interface BirdServiceInterface {
    // Get all birds
    Iterable<Bird> getAllBirds();

    // Get bird by ID
    Optional<Bird> getByBirdID(int id);

    // Add new bird
    void addBird(Bird bird);

    // Delete bird by ID
    void deleteBirdByID(int id);
}
