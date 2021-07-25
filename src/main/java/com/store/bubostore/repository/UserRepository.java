package com.store.bubostore.repository;

import com.store.bubostore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom User Service query
    // Search user by username
    @Query("SELECT user FROM User user WHERE user.username LIKE %:username%")
    User findByUsername(String username);
}