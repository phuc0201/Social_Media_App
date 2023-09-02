package com.socialmediaapp.Services;

import com.socialmediaapp.Response.ChatResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IChat {
    @POST("user/chat")
    @FormUrlEncoded
    Call<ChatResponse> loadMessage(@Field("UID_1") String My_UID, @Field("UID_2")String Friend_UID);
    @POST("user/chat/send")
    @FormUrlEncoded
    Call<ChatResponse> sendMessage(@Field("chat_id") String chat_id,
                                   @Field("sender_id") String sender_id,
                                   @Field("message") String message,
                                   @Field("timestamp") String timestamp,
                                   @Field("type") String type
    );
}
