package com.example.concertticketingapp.model;

public class Venue {
    private String id;
    private String name;
    private String address;
    private int capacity;
    private String city;

    public Venue(String id, String name, String address, int capacity, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
