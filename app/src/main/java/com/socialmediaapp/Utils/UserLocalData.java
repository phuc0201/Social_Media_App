package com.socialmediaapp.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.socialmediaapp.View.Activity.LoginActivity;
import com.socialmediaapp.Model.User;

public class UserLocalData {
    private static final String SHARED_PREF_NAME = "retrofit_auth";
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_GENDER = "key_gender";
    private static final String KEY_ID = "key_id";
    private static final String KEY_AVATAR= "key_avatar";
    private static final String KEY_BIRTHDATE = "key_birthdate";
    @SuppressLint("StaticFieldLeak")
    private static UserLocalData instance;
    @SuppressLint("StaticFieldLeak")
    private static Context ctx;
    public UserLocalData(Context context) {
        ctx = context;
    }
    public static synchronized UserLocalData getInstance(Context context){
        if(instance == null){
            instance = new UserLocalData(context);
        }
        return instance;
    }
    public void userLogin (User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_AVATAR, user.getAvatar());
        editor.putString(KEY_BIRTHDATE, user.getBirthdate());
        editor.apply();
    }
    public void updateAvatar(String avatar){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_AVATAR, avatar);
        editor.apply();
    }
    public void updateProfile(User user){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_BIRTHDATE, user.getBirthdate());
        editor.apply();
    }
    //this method will check whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null) != null;
    }
    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_BIRTHDATE, null),
                sharedPreferences.getString(KEY_AVATAR, null),
                sharedPreferences.getString(KEY_GENDER, null)
        );
    }
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(ctx, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }
}
