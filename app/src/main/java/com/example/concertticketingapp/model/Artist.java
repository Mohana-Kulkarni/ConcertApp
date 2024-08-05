package com.example.concertticketingapp.model;

public class Artist {
    private String id;
    private String name;
    private String userName;
    private String email;
    private String govId;

    public Artist(String id, String name, String userName, String email, String govId) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.govId = govId;
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
}
