package com.store.bubostore.controller;

import com.store.bubostore.entity.Bird;
import com.store.bubostore.entity.User;
import com.store.bubostore.repository.BirdRepository;
import com.store.bubostore.service.product.bird.BirdService;
import com.store.bubostore.service.user.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Controller
public class StoreController {
    @Autowired
    BirdService birdService;

    @Autowired
    UserService userService;

    // 403 page
    @GetMapping("access-denied")
    public String accessDenied() {
        return "403";
    }

    // Load product to index after login
    @GetMapping(value = {"/"})
    public String getAllBirds(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        // Get name and money
        model.addAttribute("user", user);
        // Get all product
        model.addAttribute("birds", birdService.getAllBirds());
        return "index";
    }

    // Load login form page
    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    // Load edit profile page
    // + "\\" + username + ".png"
    @GetMapping("/profile/{username}")
    public String profile(Model model, @PathVariable("username") String username) {
        File file = new File("D:\\JavaWorkspace\\BuBoStore\\src\\main\\resources\\static\\upload\\avatar\\" + username + "\\" + username + ".png");
        if (file.exists()) {
            String base64File = "";
            try (FileInputStream imageInFile = new FileInputStream(file)) {
                byte fileData[] = new byte[(int) file.length()];
                imageInFile.read(fileData);
                base64File = Base64.encodeBase64String(fileData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("avatar", base64File);
        }

        User userProfile = userService.findByUsername(username);
        model.addAttribute("userProfile", userProfile);
        return "user-profile";
    }

    // Update profile action
    @PostMapping("/profile/update/{username}")
    public String updateProfile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("about") String about,
            @PathVariable("username") String username) {

        // Update and store avatar to folder
        if (!Objects.equals(file.getOriginalFilename(), "")) {
            String path = "D:\\JavaWorkspace\\BuBoStore\\src\\main\\resources\\static\\upload\\avatar\\" + username;
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
            // Check directory exists
            File userDir = new File(path);
            if (!userDir.isDirectory()) {
                userDir.mkdir();
            }

            try {
                String fileName = username + suffix;
                File avatarPath = new File(path + "\\" + fileName);
                FileOutputStream avatar;
                avatar = new FileOutputStream(avatarPath);
                avatar.write(file.getBytes());
                avatar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Update info to database
        userService.updateProfile(name, phone, about, username);
        return "redirect:/profile/{username}";
    }

    // Cart action
    @PostMapping("/checkout")
    public String checkoutAction(Model model, @RequestParam("item[]") List<String> items, @RequestParam("user") String username) {
        int[] itemID = new int[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemID[i] = Integer.parseInt(items.get(i));
        }
        List<Bird> itemInfoList = birdService.findAllBirdByID(itemID);
        model.addAttribute("itemInfoList", itemInfoList);
        model.addAttribute("username", username);
        return "checkout";
    }

    // Pay action
    @PostMapping("/pay")
    public String pay(
            @RequestParam("itemQuantity[]") List<Integer> itemQuantitys,
            @RequestParam("itemID[]") List<Integer> itemIDs,
            @RequestParam("username") String username,
            @RequestParam("total") String totalMoney) {
        User user = userService.findByUsername(username);
        Float newMoney = user.getMoney() - Float.parseFloat(totalMoney);
        userService.updateMoney(newMoney, username);
        for (int i = 0; i < itemIDs.size(); i++) {
            List<Bird> getBirdInfo = birdService.findAllBirdByID(itemIDs.get(i));
            int currentQuantity = getBirdInfo.get(0).getQuantity();
            int newQuantity = currentQuantity - itemQuantitys.get(i);
            birdService.updateQuantityBirdByID(newQuantity, itemIDs.get(i));
        }
        return "redirect:/";
    }
}