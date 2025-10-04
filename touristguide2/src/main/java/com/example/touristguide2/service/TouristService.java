package com.example.touristguide2.service;

import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        return repository.addAttraction(attraction);
    }

    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractionsRepo();
    }

    public TouristAttraction getAttractionsByName(String name) {
        return repository.getAttractionByName(name);
    }

    public TouristAttraction updateAttraction(String name, String description, String tags, String location) {
        repository.updateAttraction(name, description,tags,location);
        return repository.getAttractionByName(name);
    }

    public TouristAttraction deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }


}
