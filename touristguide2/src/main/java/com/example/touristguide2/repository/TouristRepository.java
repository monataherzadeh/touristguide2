package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> attractions = new ArrayList<>();

    public void attractionList(){
        attractions.add(new TouristAttraction("", ""));
        attractions.add(new TouristAttraction("", ""));
        attractions.add(new TouristAttraction("", ""));
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        TouristAttraction newTouristAttraction = new TouristAttraction(attraction.getName(), attraction.getDescription());
        attractions.add(newTouristAttraction);
        return newTouristAttraction;
    }

    public ArrayList<TouristAttraction> getAllAttractions() {
        return attractions;
    }

    public TouristAttraction getAttractionByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction findAttractionByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equals(name)) {
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equals(name)) {
                attraction.setName(updatedAttraction.getName());
                attraction.setDescription(updatedAttraction.getDescription());
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction deleteAttraction(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attractions.remove(attraction);
                return attraction;
            }
        }
        return null;
    }
}
