package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class UserMap implements Serializable {
    private String title;
    private ArrayList<Place> places;

    public UserMap() {
        places = new ArrayList<>();
    }

    public UserMap(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public void addPlace(Place place) {
        this.places.add(place);
    }

    
}

