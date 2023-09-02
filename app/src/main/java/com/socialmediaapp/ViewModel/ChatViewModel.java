package com.socialmediaapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.socialmediaapp.Model.Message;
import com.socialmediaapp.Repository.ChatRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel

public class ChatViewModel extends ViewModel {
    private ChatRepository chatRepo;
    private MutableLiveData<Boolean> status;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<String> chat_id;

    @Inject
    public ChatViewModel(ChatRepository chatRepo) {
        this.chatRepo = chatRepo;
        status = chatRepo.getStatus();
        messages = chatRepo.getMessages();
        chat_id = chatRepo.getChat_id();
    }

    public ChatRepository getChatRepo() {
        return chatRepo;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public MutableLiveData<List<Message>> getMessages(String UID) {
        chatRepo.loadMessage(UID);
        return messages;
    }

    public MutableLiveData<String> getChat_id() {
        return chat_id;
    }
}
