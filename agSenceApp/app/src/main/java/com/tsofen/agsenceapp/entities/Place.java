package com.tsofen.agsenceapp.entities;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;
import java.util.Objects;

public class Place implements Serializable, ClusterItem {
    private String title;
    private String snippet;
    private float latitude;
    private float longitude;

    public Place(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(String title, String snippet, float latitude, float longitude) {
        this.title = title;
        this.snippet = snippet;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(String title, Float latitude, Float longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
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
        return new LatLng(latitude, longitude);
    }

    public void setLocation(LatLng location) {
        this.latitude = (float) location.latitude;
        this.longitude = (float) location.longitude;
    }

    @NonNull
    @Override
    public LatLng getPosition() {
        return getLocation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Float.compare(place.latitude, latitude) == 0 &&
                Float.compare(place.longitude, longitude) == 0 &&
                Objects.equals(title, place.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, latitude, longitude);
    }
}
