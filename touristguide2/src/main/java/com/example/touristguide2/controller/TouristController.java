package com.example.touristguide2.controller;


import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}