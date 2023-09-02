package com.socialmediaapp.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.socialmediaapp.Model.Message;
import com.socialmediaapp.Response.ChatResponse;
import com.socialmediaapp.Services.BaseAPIService;
import com.socialmediaapp.Services.IChat;
import com.socialmediaapp.Utils.UserLocalData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRepository {
    private MutableLiveData<Boolean> status;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<String> chat_id;
    private final Context context;

    public ChatRepository(Context context){
        this.context = context;
        messages = new MutableLiveData<>();
        status = new MutableLiveData<>();
        chat_id = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public MutableLiveData<List<Message>> getMessages() {
        return messages;
    }

    public MutableLiveData<String> getChat_id() {
        return chat_id;
    }

    public Context getContext() {
        return context;
    }



    public void loadMessage(String UID){
        BaseAPIService.createService(IChat.class).loadMessage(UserLocalData.getInstance(context).getUser().getId(), UID).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(@NonNull Call<ChatResponse> call, @NonNull Response<ChatResponse> response) {
                if (response.body() != null && response.body().getStatus().equals("success")){
                    status.setValue(true);
                    messages.setValue(response.body().getMessages());
                    chat_id.setValue(response.body().getChat_id());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChatResponse> call, @NonNull Throwable t) {
                status.setValue(false);
            }
        });
    }
}
