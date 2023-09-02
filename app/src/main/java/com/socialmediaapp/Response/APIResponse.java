package com.socialmediaapp.Response;

import com.google.gson.annotations.SerializedName;

public class APIResponse {
    @SerializedName("status") private String status;
    @SerializedName("message") private String message;

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
}
