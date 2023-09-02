package com.socialmediaapp.Model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("message")
    String message;
    @SerializedName("sender_id")
    String sender_id;
    @SerializedName("timestamp")
    String timestamp;
    @SerializedName("type")
    String type;

    public Message(){}
    public Message(String message) {
        this.message = message;    }

    public Message(String sender_id, String message, String timestamp, String type) {
        this.message = message;
        this.sender_id = sender_id;
        this.timestamp = timestamp;
        this.type = type;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
