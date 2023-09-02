package com.socialmediaapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.socialmediaapp.Model.User;
import com.socialmediaapp.Repository.ContactRespository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ContactViewModel extends ViewModel {
    private final ContactRespository contactRepo;
    private final MutableLiveData<String> message;
    private final MutableLiveData<Boolean> status;
    private final MutableLiveData<List<User>> friendList;

    @Inject
    public ContactViewModel(ContactRespository contactRepo) {
        this.contactRepo = contactRepo;
        message = contactRepo.getMessage();
        status = contactRepo.getStatus();
        friendList = contactRepo.getFriendList();
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public MutableLiveData<List<User>> getListContact() {
        contactRepo.getListContact();
        return friendList;
    }
    public MutableLiveData<List<User>> findUser() {
        contactRepo.findUser();
        return friendList;
    }
}
