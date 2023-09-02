package com.socialmediaapp.Response;

import com.google.gson.annotations.SerializedName;
import com.socialmediaapp.Model.User;

import java.util.List;

public class ContactResponse {
    @SerializedName("status") private String status;
    @SerializedName("message") private String message;
    @SerializedName("friendlist") private List<User> friendList;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }
}
