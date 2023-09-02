package com.socialmediaapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.socialmediaapp.Repository.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final UserRepository uRepo;
    private final MutableLiveData<String> message;
    private final MutableLiveData<Boolean> status;

    @Inject
    public LoginViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;

        message = uRepo.getMessage();
        status = uRepo.getStatus();
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }


    public void Login(String user_name, String user_password) {
        uRepo.Login(user_name, user_password);
    }
}
