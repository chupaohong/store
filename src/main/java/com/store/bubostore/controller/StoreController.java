package com.store.bubostore.controller;

import com.store.bubostore.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
