package com.example.touristguide2.controller;


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
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionsByName(name);
        model.addAttribute("attraction", attraction);
        return "attractionsByName";
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
        return "add";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    //The allTags key-value pair is not about the current state of the attraction, it’s about giving the view all possible options that the user can choose from.
    //the specific URL with the specific name field is accessed by a link in a former webpage
    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionsByName(name);
        if (attraction == null) {
            throw new IllegalArgumentException("Invalid attraction name: " + name);
        }
        model.addAttribute("attraction", attraction);
        return "edit";
    }

    //n this controller method, attraction is the fresh object that Spring just built from the form submission in the edit.html document.
    //Its getters will return the new user-supplied values.
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(
                attraction.getPark(),
                attraction.getInfo(),
                attraction.getTags(),
                attraction.getLocation());
        return "redirect:/attractions";
    }
    //When the object is deleted, the client is in the same instance sent to the 'same' view, but with @GetMapping, showAllAttractions(Model model) controller method called again with new list of TouristAttraction objects displayed.
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return "redirect:/attractions";
    }
}



