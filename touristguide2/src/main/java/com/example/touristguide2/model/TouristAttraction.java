package com.example.touristguide2.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String tags;
    private String location;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description, String tags, String location) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;

    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}