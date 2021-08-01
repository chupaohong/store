package com.store.bubostore.service.product.bird;

import com.store.bubostore.entity.Bird;
import com.store.bubostore.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdService implements BirdServiceInterface {
    @Autowired
    BirdRepository birdRepository;

    // Get all birds
    @Override
    public List<Bird> getAllBirds() {
        return birdRepository.findAll();
    }

    @Override
    public List<Bird> findAllBirdByID(int ...id) {
        return birdRepository.findAllBirdByID(id);
    }

    // Get bird by name
    public List<Bird> findBirdLikeName(String name) {
        return birdRepository.findBirdLikeName(name);
    }

    // Edit bird
    // Get bird by ID
    @Override
    public Optional<Bird> getByBirdID(int id) {
        return birdRepository.findById(id);
    }

    // Update bird by ID
    public void updateBirdByID(Bird bird) {
        Bird updateBird = birdRepository.getById(bird.getId());
        updateBird.setName(bird.getName());
        updateBird.setQuantity(bird.getQuantity());
        updateBird.setDescription(bird.getDescription());
        updateBird.setOriginalPrice(bird.getOriginalPrice());
        updateBird.setPrice(bird.getPrice());
        updateBird.setSpecialItem(bird.getSpecialItem());

        if (bird.getSpecialItem() == null || bird.getSpecialItem().equals(false)) {
            updateBird.setOriginalPrice((float) 0);
            updateBird.setSpecialItem(false);
        }
        birdRepository.save(updateBird);
    }

    @Override
    public void updateQuantityBirdByID(int quantity, int id) {
        birdRepository.updateQuantity(quantity, id);
    }

    // Add new bird
    @Override
    public void addBird(Bird bird) {
        if (bird.getOriginalPrice() == null || bird.getSpecialItem().equals(false)) {
            bird.setOriginalPrice((float) 0);
            bird.setSpecialItem(false);
        }
        birdRepository.save(bird);
    }

    // Delete bird by ID
    @Override
    public void deleteBirdByID(int id) {
        birdRepository.deleteById(id);
    }
}