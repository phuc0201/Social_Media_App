package com.socialmediaapp.Response;

import com.google.gson.annotations.SerializedName;
import com.socialmediaapp.Model.Post;

public class NewPostResponse {
    @SerializedName("status") private String status;
    @SerializedName("message") private String message;
    @SerializedName("post") private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
