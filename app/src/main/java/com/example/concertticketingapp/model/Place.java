package com.example.concertticketingapp.model;

public class Place {
    private String id;
    private String city;
    private boolean popular;

    public Place(String id, String city, boolean popular) {
        this.id = id;
        this.city = city;
        this.popular = popular;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cityName) {
        this.city = cityName;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }
}
