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

    public TouristAttraction getAttractionsByName(String park) {
        return repository.getAttractionByName(park);
    }

    public TouristAttraction updateAttraction(TouristAttraction attraction) {
        repository.updateAttractionWithID(attraction);
        return repository.getAttractionById(attraction.getId());
    }

    public TouristAttraction deleteAttraction(String park) {
        return repository.deleteAttraction(park);
    }
}
