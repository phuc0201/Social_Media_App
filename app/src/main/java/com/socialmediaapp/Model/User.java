package com.socialmediaapp.Model;

import com.google.gson.annotations.SerializedName;

public class User {


    @SerializedName("_id")
    private String id;


    @SerializedName("userId")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("birthdate")
    private String birthdate;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    @SerializedName("gender")
    private String gender;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("friendship")
    private Boolean friendship;
    public User(String avatar){
        this.avatar = avatar;
    }

    public User(String userId, String id){
        this.userId = userId;
        this.id = id;
    }
    public User(String id,String username, String avatar, Boolean friendship){
        this.username = username;
        this.id = id;
        this.avatar = avatar;
        this.friendship = friendship;
    }

    public User(String id, String username, String email, String birthDate, String avatar, String gender) {
        this.id = id;
        this.username = username;
        this.birthdate = birthDate;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getFriendship() {
        return friendship;
    }

    public void setFriendship(Boolean friendship) {
        this.friendship = friendship;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
