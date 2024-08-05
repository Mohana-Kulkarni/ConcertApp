package com.example.concertticketingapp.model;

public class Tier {
    private String id;
    private String name;
    private int capacity;
    private int price;

    public Tier(String id, String name, int capacity, int price) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
