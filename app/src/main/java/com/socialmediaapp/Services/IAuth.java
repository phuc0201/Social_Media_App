package com.socialmediaapp.Services;

import com.socialmediaapp.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAuth {
    @FormUrlEncoded
    @POST("auth/login")
    Call<UserResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("auth/register")
    Call<UserResponse> signup(@Field("username") String username,
                      @Field("password") String password,
                      @Field("email") String email,
                      @Field("birthdate") String birthdate,
                      @Field("avatar") String avatar,
                      @Field("gender") String gender);
}
