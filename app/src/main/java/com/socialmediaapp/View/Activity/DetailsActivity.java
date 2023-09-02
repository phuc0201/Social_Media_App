package com.socialmediaapp.View.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.socialmediaapp.R;
import com.socialmediaapp.View.Fragment.ContactFragment;
import com.socialmediaapp.View.Fragment.UserProfileFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        switch (getIntent().getIntExtra("fr", 0)) {
            case 0:
                setFragment(new ContactFragment());
                break;
            case 1:
                setFragment(UserProfileFragment.newInstance(getIntent().getStringExtra("UID")));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Details_frame, fragment);
        ft.commit();
    }
}