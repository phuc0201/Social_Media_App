package com.socialmediaapp.Services;

import com.socialmediaapp.Response.APIResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INotification {
    @FormUrlEncoded
    @POST("/notification")
    Call<APIResponse> getNotification(@Field("UID") String UID,
                                      @Field("username") String username,
                                      @Field("avatar")String avatar,
                                      @Field("content")String content);
}
