package com.socialmediaapp.Model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("_id")
    private String id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("username")
    private String username;
    @SerializedName("content")
    private String content;
    @SerializedName("date")
    private String datePublished;


    @SerializedName("avatar")
    private String avatar;

    public Comment(String userId, String username, String content, String datePublished, String avatar) {
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.datePublished = datePublished;
        this.avatar = avatar;
    }
    public Comment(String userId, String username, String content, String avatar) {
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.avatar = avatar;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }
}
