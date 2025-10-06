package com.example.touristguide2.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String tags;
    private String location;
    //private int id;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description, String tags, String location /*,int id*/) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.location = location;
        //this.id = id;
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
    public void getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}