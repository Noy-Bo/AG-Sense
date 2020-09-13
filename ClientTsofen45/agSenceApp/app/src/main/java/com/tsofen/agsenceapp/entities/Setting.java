package com.tsofen.agsenceapp.entities;

public class Setting {
    protected float longitude;
    protected float latitude;
    protected float radius;
    protected boolean type;
    protected String speed;
    protected String adminnumber;
    protected String authorizednum1;
    protected String authorizednum2;
    protected String authorizednum3;
    protected float interval1;
    protected float interval2;
    protected float distance;
    protected float heading;

    public Setting(float longitude, float latitude, float radius, boolean type, String speed, String adminnumber, String authorizednum1, String authorizednum2, String authorizednum3, float interval1, float interval2, float distance, float heading) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.type = type;
        this.speed = speed;
        this.adminnumber = adminnumber;
        this.authorizednum1 = authorizednum1;
        this.authorizednum2 = authorizednum2;
        this.authorizednum3 = authorizednum3;
        this.interval1 = interval1;
        this.interval2 = interval2;
        this.distance = distance;
        this.heading = heading;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getAdminnumber() {
        return adminnumber;
    }

    public void setAdminnumber(String adminnumber) {
        this.adminnumber = adminnumber;
    }

    public String getAuthorizednum1() {
        return authorizednum1;
    }

    public void setAuthorizednum1(String authorizednum1) {
        this.authorizednum1 = authorizednum1;
    }

    public String getAuthorizednum2() {
        return authorizednum2;
    }

    public void setAuthorizednum2(String authorizednum2) {
        this.authorizednum2 = authorizednum2;
    }

    public String getAuthorizednum3() {
        return authorizednum3;
    }

    public void setAuthorizednum3(String authorizednum3) {
        this.authorizednum3 = authorizednum3;
    }

    public float getInterval1() {
        return interval1;
    }

    public void setInterval1(float interval1) {
        this.interval1 = interval1;
    }

    public float getInterval2() {
        return interval2;
    }

    public void setInterval2(float interval2) {
        this.interval2 = interval2;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getHeading() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }
}
