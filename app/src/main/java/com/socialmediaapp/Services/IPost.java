package com.socialmediaapp.Services;

import com.socialmediaapp.Model.Post;
import com.socialmediaapp.Response.APIResponse;
import com.socialmediaapp.Response.NewPostResponse;
import com.socialmediaapp.Response.PostResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IPost {
    @GET("/post/all")
    Call<PostResponse> getListPost();
    @Multipart
    @POST("/post/create")
    Call<NewPostResponse> createPost(@Part("content") RequestBody content, @Part("userId") RequestBody UserID, @Part MultipartBody.Part Image);

    @FormUrlEncoded
    @POST("/post/togglelike")
    Call<Post> togglelike(@Field("postId") String postId, @Field("userId") String userId);
    @FormUrlEncoded
    @POST("/post/getListComment")
    Call<PostResponse> getComments(@Field("postId") String postId);

    @FormUrlEncoded
    @POST("/post/addcomment")
    Call<APIResponse> addComment(@Field("userId") String userID, @Field("postId") String postId, @Field("username") String username, @Field("content") String content);

    @FormUrlEncoded
    @POST("/post/userid")
    Call<PostResponse> getPostsbyUID(@Field("UID") String userID);
}
