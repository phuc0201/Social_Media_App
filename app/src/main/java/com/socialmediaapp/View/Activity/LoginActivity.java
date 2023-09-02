package com.socialmediaapp.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.socialmediaapp.R;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.ViewModel.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        Context ctx = this;
        if (UserLocalData.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        else
            onLogin();
    }
    private void onLogin(){
        EditText edT_email = findViewById(R.id.email);
        EditText edT_password = findViewById(R.id.password);
        AppCompatButton btn_login = findViewById(R.id.btn_login);
        TextView tv_signup = findViewById(R.id.tv_signup);
        btn_login.setOnClickListener(view -> {
            if(edT_email.getText().toString().trim().equals("") || edT_password.getText().toString().trim().equals("")
            || edT_email.getText().toString().isEmpty() || edT_password.getText().toString().isEmpty()){

                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
            }
            else if(checkValid()){
                String email = edT_email.getText().toString().trim();
                String password = edT_password.getText().toString().trim();
                loginViewModel.Login(email, password);
                loginViewModel.getStatus().observe(this, status -> {
                    if (status) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

        });
        tv_signup.setOnClickListener(view -> {
            Intent registerActivity = new Intent(this, RegisterActivity.class);
            startActivity(registerActivity);
        });
    }
    public boolean checkValid(){
        return true;
    }

}