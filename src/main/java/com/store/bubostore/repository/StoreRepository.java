package com.store.bubostore.repository;

import com.store.bubostore.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Bird, Integer> {
    // Bird Service
    // Search by product name
    @Query("SELECT bird FROM Bird bird WHERE bird.name LIKE %:name%")
    List<Bird> findByName(String name);
}