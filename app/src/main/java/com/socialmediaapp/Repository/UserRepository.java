package com.socialmediaapp.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.socialmediaapp.Model.User;
import com.socialmediaapp.Response.UserResponse;
import com.socialmediaapp.Services.BaseAPIService;
import com.socialmediaapp.Services.IAuth;
import com.socialmediaapp.Utils.UserLocalData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private MutableLiveData<User> user;
    private final MutableLiveData<String> message; // toast messages
    private final MutableLiveData<Boolean> status; // proccess status for navigation
    private final Context context;
    public UserRepository(Context context){
        this.context = context;
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
    }

    public MutableLiveData<User> getUser() {
        return user;
    }
    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }
    public void Login(String email,String password){
        BaseAPIService.createService(IAuth.class).login(email, password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.body()!=null && response.body().getStatus().equals("success")) {
                    User userJson = response.body().getUser();
                    UserLocalData.getInstance(context).userLogin(new User(
                            userJson.getId(),
                            userJson.getUsername(),
                            userJson.getEmail(),
                            userJson.getBirthdate(),
                            userJson.getAvatar(),
                            userJson.getGender()
                    ));
                    status.setValue(true);
                    message.setValue(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                status.setValue(false);
                message.setValue("");
            }
        });
    }

}