package com.example.concertticketingapp.model;

import java.util.List;

public class Event {

    private String id;
    private String name;
    private String description;
    private String dateAndTime;
    private String eventDuration;
    private List<String> imageUrls;
    private List<String> categoryList;
    private Venue venueId;
    private List<Artist> artistList;
    private List<Tier> tierList;

    public Event(String id, String name, String description, String dateAndTime, String eventDuration, List<String> imageUrls,List<String> categoryList, Venue venueId, List<Artist> artistList, List<Tier> tierList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.eventDuration = eventDuration;
        this.imageUrls = imageUrls;
        this.categoryList = categoryList;
        this.venueId = venueId;
        this.artistList = artistList;
        this.tierList = tierList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(String eventDuration) {
        this.eventDuration = eventDuration;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public Venue getVenueId() {
        return venueId;
    }

    public void setVenueId(Venue venueId) {
        this.venueId = venueId;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public List<Tier> getTierList() {
        return tierList;
    }

    public void setTierList(List<Tier> tierList) {
        this.tierList = tierList;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
