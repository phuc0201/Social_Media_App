package com.socialmediaapp.View.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.socialmediaapp.View.Fragment.AddFriendFragment;
import com.socialmediaapp.View.Fragment.FeedFragment;
import com.socialmediaapp.View.Fragment.NewPostFragment;
import com.socialmediaapp.View.Fragment.NotificationFragment;
import com.socialmediaapp.View.Fragment.SettingFragment;

import java.util.ArrayList;

public class TabAdapter extends FragmentStateAdapter {
    private final ArrayList<Integer> listTab;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Integer> listTab) {
        super(fragmentActivity);
        this.listTab = listTab;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new AddFriendFragment();
            case 2:
                return new NewPostFragment();
            case 3:
                return new NotificationFragment();
            case 4:
                return new SettingFragment();
            default:
                return new FeedFragment();
        }
    }

    @Override
    public int getItemCount() {
        return listTab.size();
    }
}
