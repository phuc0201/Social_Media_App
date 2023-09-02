package com.socialmediaapp.Services;

import com.socialmediaapp.Response.APIResponse;
import com.socialmediaapp.Response.ContactResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IContact {
    @FormUrlEncoded
    @POST("/user/contacts")
    Call<ContactResponse> getListContact(@Field("userID") String userID);
    @FormUrlEncoded
    @POST("/user")
    Call<ContactResponse> findUser(@Field("UID") String userID);
    @FormUrlEncoded
    @POST("/user/addfriend")
    Call<APIResponse> addFriend(@Field("userId") String userID, @Field("friendId") String friendID);
}
