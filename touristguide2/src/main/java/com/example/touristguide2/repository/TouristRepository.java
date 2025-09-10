package com.example.touristguide2.repository;

import com.example.touristguide2.model.Tags;
import com.example.touristguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> attractions = new ArrayList<>();

    public void attractionList() {

        attractions.add(new TouristAttraction("The Rainbow Mountains", "multicolored rock formations resemble brushstrokes across the landscape", Arrays.asList(Tags.ART, Tags.FREE, Tags.NATURE)));
        attractions.add(new TouristAttraction("Kangding", "A mountain town rich in Tibetan culture and alpine scenery", Arrays.asList(Tags.NATURE, Tags.FREE, Tags.MUSEUM)));
        attractions.add(new TouristAttraction("Tulou Villages", "fortress-like communal homes built by the Hakka people date back to the 12th century", Arrays.asList(Tags.NATURE, Tags.HISTORY, Tags.ARCHITECTURE)));
        attractions.add(new TouristAttraction("TheRainbowMountains", "multicolored rock formations resemble brushstrokes across the landscape", Arrays.asList(Tags.ART, Tags.FREE, Tags.NATURE)));
        attractions.add(new TouristAttraction("Kangding", "A mountain town rich in Tibetan culture and alpine scenery", Arrays.asList(Tags.NATURE, Tags.FREE, Tags.MUSEUM)));
        attractions.add(new TouristAttraction("Tulouvillages", "fortress-like communal homes built by the Hakka people date back to the 12th century", Arrays.asList(Tags.NATURE, Tags.HISTORY, Tags.ARCHITECTURE)));

    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        TouristAttraction newTouristAttraction = new TouristAttraction(attraction.getName(), attraction.getDescription(), attraction.getTags());
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
