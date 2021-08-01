package com.store.bubostore.service.product.bird;

import com.store.bubostore.entity.Bird;

import java.util.List;
import java.util.Optional;

public interface BirdServiceInterface {
    // Get all birds
    List<Bird> getAllBirds();

    // Get bird by name
    List<Bird> findAllBirdByID(int ...id);

    // Get bird like name
    List<Bird> findBirdLikeName(String name);

    // Get bird by ID
    Optional<Bird> getByBirdID(int id);

    // Update bird by ID
    void updateBirdByID(Bird bird);

    // Update quantity bird by ID
    void updateQuantityBirdByID(int quantity, int id);

    // Add new bird
    void addBird(Bird bird);

    // Delete bird by ID
    void deleteBirdByID(int id);
}
