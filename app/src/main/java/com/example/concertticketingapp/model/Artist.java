package com.example.concertticketingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Artist implements Parcelable {
    private String id;
    private String name;
    private String userName;
    private String email;
    private String govId;
    private String profileImg;

    public Artist(String id, String name, String userName, String email, String govId, String profileImg) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.govId = govId;
        this.profileImg = profileImg;
    }

    protected Artist(Parcel in) {
        id = in.readString();
        name = in.readString();
        userName = in.readString();
        email = in.readString();
        govId = in.readString();
        profileImg = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGovId() {
        return govId;
    }

    public void setGovId(String govId) {
        this.govId = govId;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(userName);
        parcel.writeString(govId);
        parcel.writeString(profileImg);
        parcel.writeString(email);
    }
}
