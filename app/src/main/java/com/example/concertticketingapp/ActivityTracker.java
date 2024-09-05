package com.example.concertticketingapp;

public class ActivityTracker {
    private static ActivityTracker instance;
    private String lastActivityName;

    private ActivityTracker() {}

    public static ActivityTracker getInstance() {
        if(instance == null) {
            instance = new ActivityTracker();
        }
        return instance;
    }

    public void setLastActivityName(String name) {
        this.lastActivityName = name;
    }
    public String getLastActivityName() {
        return this.lastActivityName;
    }
}
