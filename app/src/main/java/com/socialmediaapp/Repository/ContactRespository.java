package com.socialmediaapp.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.socialmediaapp.Model.User;
import com.socialmediaapp.Response.APIResponse;
import com.socialmediaapp.Response.ContactResponse;
import com.socialmediaapp.Services.BaseAPIService;
import com.socialmediaapp.Services.IContact;
import com.socialmediaapp.Utils.UserLocalData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRespository {

    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> status;
    private MutableLiveData<List<User>> friendList;
    private final Context context;

    public MutableLiveData<String> getMessage() {
        return message;
    }
    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public MutableLiveData<List<User>> getFriendList() {
        return friendList;
    }


    public ContactRespository(Context context){
        this.context = context;
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
        friendList = new MutableLiveData<>();
    }
    public void getListContact(){
        if(UserLocalData.getInstance(context).isLoggedIn()){
            String userID = UserLocalData.getInstance(context).getUser().getId();
            BaseAPIService.createService(IContact.class).getListContact(userID).enqueue(new Callback<ContactResponse>() {
                @Override
                public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                    if (response.body() != null && response.body().getStatus().equals("success")){
                        friendList.setValue(response.body().getFriendList());
                        status.setValue(true);
                        message.setValue(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ContactResponse> call, Throwable t) {
                    status.setValue(false);
                    message.setValue("");
                }
            });
        }
    }
    public void findUser(){
        String userID = UserLocalData.getInstance(context).getUser().getId();
        BaseAPIService.createService(IContact.class).findUser(userID).enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(@NonNull Call<ContactResponse> call, @NonNull Response<ContactResponse> response) {
                if (response.body() != null && response.body().getStatus().equals("success")){
                    friendList.setValue(response.body().getFriendList());
                    status.setValue(true);
                    message.setValue(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ContactResponse> call, @NonNull Throwable t) {
                status.setValue(false);
                message.setValue("");
            }
        });
    }
    public void addFriend(String friendID){
        String userID = UserLocalData.getInstance(context).getUser().getId();
        BaseAPIService.createService(IContact.class).addFriend(userID,friendID).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.body() != null && response.body().getStatus().equals("success")){
                    status.setValue(true);
                    message.setValue(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                status.setValue(false);
                message.setValue("");
            }
        });
    }
}
