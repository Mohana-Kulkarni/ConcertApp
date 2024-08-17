package com.example.concertticketingapp.model;

import java.util.Map;

public class Ticket {
    private String id;
    private int count;
    private String cost;
    private User user;
    private String vcId;
    private Tier tier;
    private Event eventId;
    private String transactionId;
    private Map<String, Map<String, String>> nfts;

    public Ticket(int count, String cost, User user, String vcId, Tier tier, Event eventId, String transactionId, Map<String, Map<String, String>> nfts) {
        this.count = count;
        this.cost = cost;
        this.user = user;
        this.vcId = vcId;
        this.tier = tier;
        this.eventId = eventId;
        this.transactionId = transactionId;
        this.nfts = nfts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVcId() {
        return vcId;
    }

    public void setVcId(String vcId) {
        this.vcId = vcId;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Map<String, Map<String, String>> getNfts() {
        return nfts;
    }

    public void setNfts(Map<String, Map<String, String>> nfts) {
        this.nfts = nfts;
    }
}