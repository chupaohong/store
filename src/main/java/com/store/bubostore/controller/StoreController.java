package com.store.bubostore.controller;

import com.store.bubostore.service.product.bird.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreController {
    @Autowired
    BirdService birdService;

    // Load product to index
    @GetMapping(value = {"/home", "/"})
    public String getAllBirds(Model model) {
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
}
