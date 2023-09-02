package com.socialmediaapp.Response;

import com.google.gson.annotations.SerializedName;
import com.socialmediaapp.Model.Notification;

import java.util.List;

public class NotificationResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("notifications")
    private List<Notification> notifications;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
