package com.example.touristguide2.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<Tags> tags;
    private String location;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description, List<Tags> tags, String location) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.location = location;

    }

    public String getLocation() {
        return location;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

}