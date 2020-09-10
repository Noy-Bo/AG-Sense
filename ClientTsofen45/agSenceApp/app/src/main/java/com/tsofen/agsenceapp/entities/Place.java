package com.tsofen.agsenceapp.entities;

import java.io.Serializable;

public class Place implements Serializable {
    private String title;
    private String snippet;
    private Double latitude;
    private Double longitude;

    public Place(Double latitude, Double longitude) {
        this.title = "";
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public Place(String title, String snippet, Double latitude, Double longitude) {
        this.snippet = snippet;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
