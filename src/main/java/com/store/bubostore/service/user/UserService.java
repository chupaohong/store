package com.store.bubostore.service.user;

import com.store.bubostore.entity.User;
import com.store.bubostore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getByUserID(int id) {
        return userRepository.findById(id);
    }


    @Override
    public void updateUserByID(User user) {
        User updateUser = userRepository.getById(user.getId());
        updateUser.setName(user.getName());
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        updateUser.setAbout(user.getAbout());
        updateUser.setActive(user.getActive());
        updateUser.setMoney(user.getMoney());
        if (user.getRole().equals("user") || user.getRole() == null) {
            updateUser.setRole("ROLE_USER");
        } else {
            updateUser.setRole("ROLE_ADMIN");
        }
        userRepository.save(updateUser);
    }

    // User update profile
    @Override
    public void updateProfile(String name, String phone, String about, String username) {
        userRepository.updateProfile(name, phone, about, username);
    }

    @Override
    public void updateMoney(Float money, String username) {
        userRepository.updateMoney(money, username);
    }

    @Override
    public void addUser(User user) {
        if (user.getRole().equals("user") || user.getRole().equals("ROLE_USER")) {
            user.setRole("ROLE_USER");
        } else {
            user.setRole("ROLE_ADMIN");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserByID(int id) {
        userRepository.deleteById(id);
    }
}
