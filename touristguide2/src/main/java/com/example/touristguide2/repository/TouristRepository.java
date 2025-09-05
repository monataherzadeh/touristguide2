package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> attractions = new ArrayList<>();

    public void attractionList(){
        attractions.add(new TouristAttraction("The Rainbow Mountains in Zhangye Danxia (Gansu Province)", "These striking, multicolored rock formations look like brushstrokes of paint across the landscape. It’s far less crowded than places like the Great Wall or Guilin but unforgettable if you’re into surreal natural beauty. "));
        attractions.add(new TouristAttraction("Kangding in Sichuan Province.", " It’s a mountain town on the edge of the Tibetan Plateau, known for Tibetan culture, monasteries, and dramatic alpine scenery. Visitors often pass it by on their way to more famous spots, but it offers a raw, authentic glimpse of Tibetan-Chinese border culture. "));
        attractions.add(new TouristAttraction("Tulou villages in Fujian Province.", "These are centuries-old, fortress-like circular houses built by the Hakka people. They’re a mix of communal living and defensive architecture, tucked away in the countryside, and feel worlds apart from the typical tourist trail. "));
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
