package com.socialmediaapp.Response;

import com.google.gson.annotations.SerializedName;
import com.socialmediaapp.Model.Message;

import java.util.List;

public class ChatResponse {
    @SerializedName("status")
    String status;
    @SerializedName("chat_id")
    String chat_id;
    @SerializedName("messages")
    List<Message> messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
