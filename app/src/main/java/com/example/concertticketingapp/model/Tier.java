package com.example.concertticketingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Tier implements Parcelable {
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

    protected Tier(Parcel in) {
        id = in.readString();
        name = in.readString();
        capacity = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Tier> CREATOR = new Creator<Tier>() {
        @Override
        public Tier createFromParcel(Parcel in) {
            return new Tier(in);
        }

        @Override
        public Tier[] newArray(int size) {
            return new Tier[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeInt(capacity);
        parcel.writeInt(price);
    }
}
