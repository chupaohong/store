package com.store.bubostore.repository;

import com.store.bubostore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom User Service query
    // Search user by username
    @Query("SELECT user FROM User user WHERE user.username LIKE %:username%")
    User findByUsername(String username);

    // Update profile
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.name=:name, user.phone=:phone, user.about=:about WHERE user.username=:username")
    void updateProfile(@Param("name") String name, @Param("phone") String phone, @Param("about") String about, @Param("username") String username);

    // Update money
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.money=:money WHERE user.username=:username")
    void updateMoney(Float money, String username);
}