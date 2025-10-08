package com.example.touristguide2.model;

import java.util.List;

public class TouristAttraction {
    private String park;
    private String info;
    private List<String> tags;
    private String location;
    private int id;

    public TouristAttraction() {
    }

    public TouristAttraction(String park, String info, List<String> tags, String location, int id) {
        this.park = park;
        this.info = info;
        this.tags = tags;
        this.location = location;
        this.id = id;
    }

    public String getPark() {
        return park;
    }

    public String getInfo() {
        return info;

    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}