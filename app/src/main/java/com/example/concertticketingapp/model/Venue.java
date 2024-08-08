package com.example.concertticketingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Venue implements Parcelable {
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

    protected Venue(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
        capacity = in.readInt();
        city = in.readString();
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeInt(capacity);
        parcel.writeString(city);
    }
}
