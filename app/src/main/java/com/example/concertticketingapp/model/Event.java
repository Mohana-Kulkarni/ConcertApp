package com.example.concertticketingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String dateAndTime;
    private String eventDuration;
    private List<String> imageUrls;
    private List<String> categoryList;
    private Venue venueId;
    private List<Artist> artists;
    private List<Tier> tiers;

    public Event(String id, String name, String description, String dateAndTime, String eventDuration, List<String> imageUrls,List<String> categoryList, Venue venueId, List<Artist> artistList, List<Tier> tiers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.eventDuration = eventDuration;
        this.imageUrls = imageUrls;
        this.categoryList = categoryList;
        this.venueId = venueId;
        this.artists = artistList;
        this.tiers = tiers;
    }

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        dateAndTime = in.readString();
        eventDuration = in.readString();
        imageUrls = in.createStringArrayList();
        categoryList = in.createStringArrayList();
        venueId = in.readParcelable(Venue.class.getClassLoader());
        artists = new ArrayList<>();
        in.readList(artists, Artist.class.getClassLoader());
        tiers = new ArrayList<>();
        in.readList(tiers, Tier.class.getClassLoader());
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };


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
        return artists;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artists = artistList;
    }

    public List<Tier> getTiers() {
        return tiers;
    }

    public void setTiers(List<Tier> tiers) {
        this.tiers = tiers;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(dateAndTime);
        parcel.writeString(eventDuration);
        parcel.writeStringList(imageUrls);
        parcel.writeStringList(categoryList);
        parcel.writeParcelable((Parcelable) venueId, i);
        parcel.writeList(artists);
        parcel.writeList(tiers);
    }
}
