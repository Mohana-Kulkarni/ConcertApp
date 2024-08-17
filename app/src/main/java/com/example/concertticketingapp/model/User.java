package com.example.concertticketingapp.model;

public class User {

    private String id;
    private String userEmail;
    private String walletId;
    private String transactionId;
    private String profileImg;
    private String userDetailsI;

    public User(String id, String userEmail, String walletId, String transactionId, String profileImg, String userDetailsI) {
        this.id = id;
        this.userEmail = userEmail;
        this.walletId = walletId;
        this.transactionId = transactionId;
        this.profileImg = profileImg;
        this.userDetailsI = userDetailsI;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserDetailsI() {
        return userDetailsI;
    }

    public void setUserDetailsI(String userDetailsI) {
        this.userDetailsI = userDetailsI;
    }
}
