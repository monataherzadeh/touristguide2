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
        attractions.add(new TouristAttraction("Yellow Stone Nation Park", "Yellowstone National Park is a geothermal wonderland known for its geysers, hot springs, and abundant wildlife.", Arrays.asList(Tags.WATERFALL, Tags.FOREST, Tags.HIKING)));
        attractions.add(new TouristAttraction("Grand Canyon National Park", "Grand Canyon National Park showcases breathtaking vistas of one of the world’s largest and most iconic canyons.", Arrays.asList(Tags.DESERT, Tags.MOUNTAINS, Tags.MOUNTAINS)));
        attractions.add(new TouristAttraction("Joshua Tree National Park", "Joshua Tree National Park is a desert landscape famed for its rugged rock formations and unique Joshua trees.", Arrays.asList(Tags.FOREST, Tags.MOUNTAINS, Tags.HIKING)));
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
