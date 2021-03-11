package com.avsystem.anjay.airqualitymeter.services;

public class Location {
    private final Float lat;
    private final Float lon;
    private final String city;

    public Location(Float lat, Float lon, String city) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }

    public String getCity() {
        return city;
    }
}
