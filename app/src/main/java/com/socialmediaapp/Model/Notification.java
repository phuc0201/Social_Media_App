package com.socialmediaapp.Model;

import com.google.gson.annotations.SerializedName;

public class Notification {
    @SerializedName("UID")
    String userID;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("content")
    String content;

    public Notification(String userID, String avatar, String content) {
        this.userID = userID;
        this.avatar = avatar;
        this.content = content;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
