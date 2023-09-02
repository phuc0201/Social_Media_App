package com.socialmediaapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
    @SerializedName("_id")
    private String id;
    @SerializedName("content")
    private String content;
    @SerializedName("status")
    private String status;
    @SerializedName("title")
    private String title;
    @SerializedName("userId")
    private String userID;
    @SerializedName("datePublished")
    private String datePublished;
    @SerializedName("image")
    private String image;
    @SerializedName("username")
    private String username;


    @SerializedName("like")
    private List<User> likeList;

    @SerializedName("numLikes")
    private String numLikes;

    @SerializedName("avatar")
    private String avatar;



    @SerializedName("comments")
    private List<Comment> commentList;
    public Post(){}

    public Post(String id, String userID, String title, String status, String content, String datePublished, String image, String avatar, String username, List<Comment> commentList, List<User> likeList, String numLikes){
        this.id = id;
        this.content = content;
        this.status = status;
        this.title = title;
        this.userID = userID;
        this.datePublished = String.valueOf(datePublished);
        this.image = image;
        this.username = username;
        this.avatar = avatar;
        this.likeList = likeList;
        this.commentList = commentList;
        this.numLikes = numLikes;
    }
    public Post(String content, String datePublished, String image){
        this.content =content;
        this.datePublished = datePublished;
        this.image = image;
    }
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    public List<User> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<User> likeList) {
        this.likeList = likeList;
    }

    public String getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(String numLikes) {
        this.numLikes = numLikes;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

