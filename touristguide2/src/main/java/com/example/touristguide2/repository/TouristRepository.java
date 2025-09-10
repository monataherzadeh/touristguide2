package com.example.touristguide2.repository;

import com.example.touristguide2.model.Tags;
import com.example.touristguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> attractions = new ArrayList<>();

    public void attractionList() {
        attractions.add(new TouristAttraction("Yellow Stone Nation Park", "Yellowstone National Park is a geothermal wonderland known for its geysers, hot springs, and abundant wildlife.", Arrays.asList(Tags.WATERFALL, Tags.FOREST, Tags.HIKING), "Wyoming"));
        attractions.add(new TouristAttraction("Grand Canyon National Park", "Grand Canyon National Park showcases breathtaking vistas of one of the world’s largest and most iconic canyons.", Arrays.asList(Tags.DESERT, Tags.MOUNTAINS, Tags.MOUNTAINS), "Arizona"));
        attractions.add(new TouristAttraction("Joshua Tree National Park", "Joshua Tree National Park is a desert landscape famed for its rugged rock formations and unique Joshua trees.", Arrays.asList(Tags.FOREST, Tags.MOUNTAINS, Tags.HIKING), "California"));
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        TouristAttraction newTouristAttraction = new TouristAttraction(attraction.getName(), attraction.getDescription(), attraction.getTags(), attraction.getLocation());
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
    //update attraction and return the attraction with renewed data for its fields
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
    //Update attraction and return the boolean on whether the update was successful
    //HVORDAN FÅR MAN DISSE VÆRDIER IND I DENNE METODE?
    public boolean updateAttractionBoolean(String name, String newDescription, List<Tags> tags, String newLocation) {
        for(TouristAttraction tempAttraction : attractions) {
            if(tempAttraction.getName().equals(name)) {
                tempAttraction.setDescription(newDescription);
                tempAttraction.setLocation(newLocation);
                tempAttraction.setTags(tags);
                return true;
            }
        }
        return false;
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
