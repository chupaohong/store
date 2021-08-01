package com.store.bubostore.controller;

import com.store.bubostore.entity.Bird;
import com.store.bubostore.service.product.bird.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class BirdController {
    @Autowired
    BirdService birdService;

    // Show product dashboard
    @GetMapping(value = {"/product", "/", ""})
    public String getAllBirds(Model model) {
        model.addAttribute("birdLists", birdService.getAllBirds());
        return "product-list";
    }

    // Search product by name
    @GetMapping("/bird")
    public String getByBirdName(Model model, @RequestParam("name") String name) {
        List<Bird> birdList = birdService.findBirdLikeName(name);
        model.addAttribute("birdLists", birdList);
        return "product-list";
    }

    // Search bird by ID for edit action
    @GetMapping("/bird/edit/{id}")
    public String findByBirdId(Model model, @PathVariable("id") int id) {
        Optional<Bird> birdListByID = birdService.getByBirdID(id);
        model.addAttribute("birdLists", birdListByID.orElse(null));
        return "edit-product";
    }
    // Edit bird action
    @PostMapping("/bird/edit/{id}")
    public String updateProduct(@ModelAttribute("bird") Bird bird) {
        birdService.updateBirdByID(bird);
        return "redirect:/dashboard/product";
    }

    // Get add bird page
    @GetMapping("/add/bird")
    public String addNewBird() {
        return "add-product";
    }
    // Add new bird action
    @PostMapping("/add/bird")
    public String addBird(@ModelAttribute Bird bird) {
        birdService.addBird(bird);
        return "redirect:/dashboard/product";
    }

    // Delete bird by ID
    @PostMapping("/delete/bird/{id}")
    public String deleteBirdById(@PathVariable("id") int id) {
        birdService.deleteBirdByID(id);
        return "redirect:/dashboard/product";
    }
}