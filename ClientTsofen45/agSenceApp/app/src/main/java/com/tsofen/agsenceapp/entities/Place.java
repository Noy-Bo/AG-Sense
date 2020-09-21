package com.tsofen.agsenceapp.entities;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

public class Place implements Serializable, ClusterItem {
    private String title;
    private String snippet;
    private LatLng location;

    public Place(Float latitude, Float longitude) {
        this.title = "";
        this.location = new LatLng(latitude, longitude);
    }

    public Place(String title, Float latitude, Float longitude) {
        this.title = title;
        this.location = new LatLng(latitude, longitude);
    }

    public Place(String title, String snippet, Float latitude, Float longitude) {
        this.snippet = snippet;
        this.title = title;
        this.location = new LatLng(latitude, longitude);
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    @NonNull
    @Override
    public LatLng getPosition() {
        return location;
    }
}
