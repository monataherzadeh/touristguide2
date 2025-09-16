package com.example.touristguide2.service;

import com.example.touristguide2.model.Tags;
import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristService {
    TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
        repository.attractionList();
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        return repository.addAttraction(attraction);
    }

    public ArrayList<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractionsRepo();
    }

    public TouristAttraction getAttractionsByName(String name) {
        return repository.getAttractionByName(name);
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        return repository.updateAttraction(name, updatedAttraction);
    }

    public TouristAttraction deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }
    public boolean updateAttractionBoolean(String name, String newDescription, List<Tags> newTags, String newLocation) {
        return repository.updateAttractionBoolean(name, newDescription, newTags, newLocation);
    }

}
