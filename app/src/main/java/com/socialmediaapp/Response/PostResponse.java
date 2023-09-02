package com.socialmediaapp.Response;

import com.google.gson.annotations.SerializedName;
import com.socialmediaapp.Model.Comment;
import com.socialmediaapp.Model.Post;
import com.socialmediaapp.ViewModel.PostViewModel;

import java.util.List;

public class PostResponse {
    PostViewModel postViewModel;
    @SerializedName("status") private String status;
    @SerializedName("message") private String message;
    @SerializedName("listPost") private List<Post> listPost;
    @SerializedName("comments") private List<Comment> comments;

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

    public List<Post> getListPost() {
        return listPost;
    }


    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
