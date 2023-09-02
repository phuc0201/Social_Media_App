package com.socialmediaapp.DI;

import android.content.Context;

import com.socialmediaapp.Repository.ChatRepository;
import com.socialmediaapp.Repository.ContactRespository;
import com.socialmediaapp.Repository.PostRepository;
import com.socialmediaapp.Repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public UserRepository provideUserRepository(@ApplicationContext Context context) {
        return new UserRepository(context);
    }
    @Provides
    @Singleton
    public ContactRespository provideContactRepository(@ApplicationContext Context context){
        return new ContactRespository(context);
    }
    @Provides
    @Singleton
    public ChatRepository provideChatRepository(@ApplicationContext Context context){
        return new ChatRepository(context);
    }
    @Provides
    @Singleton
    public PostRepository providePostRepository(@ApplicationContext Context context){
        return new PostRepository(context);
    }
}
