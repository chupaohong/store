package com.store.bubostore.controller;

import com.store.bubostore.entity.User;
import com.store.bubostore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class UserController {
    @Autowired
    UserService userService;

    // Show all user
    @GetMapping("/user-list")
    public String getAllUsers(Model model) {
        model.addAttribute("userLists", userService.getAllUsers());
        return "user-list";
    }

    // Search user by username
    @GetMapping("/user")
    public String getUserByUsername(Model model, @RequestParam("username") String username) {
        User userList = userService.findByUsername(username);
        model.addAttribute("userLists", userList);
        return "user-list";
    }

    // Search user by ID for edit action
    // Get edit user page
    @GetMapping("/user/edit/{id}")
    public String getByUserID(Model model, @PathVariable("id") int id) {
        Optional<User> userListByID = userService.getByUserID(id);
        model.addAttribute("userLists", userListByID.orElse(null));
        return "edit-user";
    }
    // Edit user action
    @PostMapping("/user/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUserByID(user);
        return "redirect:/dashboard/user-list";
    }

    // Add new user
    // Get add user page
    @GetMapping("/add/user")
    public String getAddUserPage() {
        return "add-user";
    }
    // Add new user action
    @PostMapping("/add/user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/dashboard/user-list";
    }

    // Delete user by ID
    @PostMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserByID(id);
        return "redirect:/dashboard/user-list";
    }
}
