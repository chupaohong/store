package com.store.bubostore.service;

import com.store.bubostore.entity.Bird;
import com.store.bubostore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdService implements BirdServiceInterface {
    @Autowired
    StoreRepository storeRepository;

    // Get all birds
    @Override
    public Iterable<Bird> getAllBirds() {
        return storeRepository.findAll();
    }

    // Get bird by name
    public List<Bird> findByBirdName(String name) {
        return storeRepository.findByName(name);
    }

    // Edit bird
    // Get bird by ID
    @Override
    public Optional<Bird> getByBirdID(int id) {
        return storeRepository.findById(id);
    }
    // Update bird by ID
    public void updateBirdByID(Bird bird) {
        Bird updateBird = storeRepository.getById(bird.getId());
        updateBird.setName(bird.getName());
        updateBird.setQuantity(bird.getQuantity());
        updateBird.setDescription(bird.getDescription());
        updateBird.setOriginalPrice(bird.getOriginalPrice());
        updateBird.setPrice(bird.getPrice());
        updateBird.setSpecialItem(bird.getSpecialItem());

        if (bird.getSpecialItem() == null || bird.getSpecialItem().equals(false)) {
            updateBird.setOriginalPrice((float) 0);
            updateBird.setSpecialItem(false);
            storeRepository.save(updateBird);
        } else {
            storeRepository.save(updateBird);
        }
    }

    // Add new bird
    @Override
    public void addBird(Bird bird) {
        if (bird.getOriginalPrice() == null || bird.getSpecialItem().equals(false)) {
            bird.setOriginalPrice((float) 0);
            bird.setSpecialItem(false);
            storeRepository.save(bird);
        } else {
            storeRepository.save(bird);
        }
    }

    // Delete bird by ID
    @Override
    public void deleteBirdByID(int id) {
        storeRepository.deleteById(id);
    }
}