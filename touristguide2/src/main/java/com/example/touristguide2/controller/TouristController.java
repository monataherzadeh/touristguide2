package com.example.touristguide2.controller;


import com.example.touristguide2.model.Tags;
import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public String showAllAttractions(Model model) {
        List<TouristAttraction> attraction = touristService.getAllAttractions();
        model.addAttribute("attraction",attraction);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String showTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionsByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction attraction = new TouristAttraction();
        model.addAttribute("attraction", attraction);
        model.addAttribute("allTags", Tags.values());
        return "add";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionsByName(name);
        if (attraction == null) {
            throw new IllegalArgumentException("Invalid attraction name: " + name);
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("allTags", Tags.values());
        return "edit";
    }

}