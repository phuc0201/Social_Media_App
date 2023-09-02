
package com.socialmediaapp.View.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.socialmediaapp.R;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Adapter.TabAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends FragmentActivity {
    private ViewPager2 viewPager2;
    private ArrayList<Integer> listTab;
    private BottomNavigationView bottomNavigationView;
    private TabAdapter tabAdapter;
    private FloatingActionButton new_post;
    private static Boolean exitApp = false;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new_post = findViewById(R.id.new_post);
        new_post.setOnClickListener(click->{
            viewPager2.setCurrentItem(listTab.size()/2);
        });
        if (!UserLocalData.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        else {
            CreateTab();
            SwitchTabs();
        }
    }


    private void CreateTab(){
        listTab = new ArrayList<>();
        listTab.add(R.id.menu_tab_home);
        listTab.add(R.id.menu_tab_addFriend);
        listTab.add(R.id.menu_tab_new_post);
        listTab.add(R.id.menu_tab_notification);
        listTab.add(R.id.menu_tab_setting);
    }
    private void SwitchTabs(){
        viewPager2 = findViewById(R.id.view_pager_2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        tabAdapter = new TabAdapter(this, listTab);
        viewPager2.setAdapter(tabAdapter);
        viewPager2.setOffscreenPageLimit(listTab.size());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            viewPager2.setCurrentItem(listTab.indexOf(item.getItemId()));
            return true;
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().findItem(listTab.get(position)).setChecked(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(viewPager2.getCurrentItem()>0){
            viewPager2.setCurrentItem(0);
        }
        else super.onBackPressed();
    }
}