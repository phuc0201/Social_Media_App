package com.socialmediaapp.Services;

import com.socialmediaapp.Response.APIResponse;
import com.socialmediaapp.Response.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IUserprofile {
    @FormUrlEncoded
    @POST("/user/profile/infor")
    Call<APIResponse> updateprofile(@Field("UID")String UID, @Field("username") String username, @Field("email") String email, @Field("birthdate") String birthdate);
    @Multipart
    @POST("/user/profile/avatar")
    Call<UserResponse> updateavatar(@Part("userID") RequestBody userID, @Part MultipartBody.Part Image);

}
