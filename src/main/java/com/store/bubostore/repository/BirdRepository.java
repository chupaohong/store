package com.store.bubostore.repository;

import com.store.bubostore.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Integer> {
    // Custom Bird Service query
    // Search product like name
    @Query("SELECT bird FROM Bird bird WHERE bird.name LIKE %:name%")
    List<Bird> findBirdLikeName(String name);

    // Search product by name
    @Query("SELECT bird FROM Bird bird WHERE bird.id in (:id)")
    List<Bird> findAllBirdByID(int ...id);

    // Update quantity
    @Transactional
    @Modifying
    @Query("Update Bird bird SET bird.quantity=:quantity WHERE bird.id=:id")
    void updateQuantity(int quantity, int id);
}