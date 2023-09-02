// Generated by view binder compiler. Do not edit!
package com.socialmediaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.socialmediaapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final RelativeLayout mainActivity;

  @NonNull
  public final FloatingActionButton newPost;

  @NonNull
  public final ViewPager2 viewPager2;

  private ActivityMainBinding(@NonNull RelativeLayout rootView,
      @NonNull BottomNavigationView bottomNavigation, @NonNull RelativeLayout mainActivity,
      @NonNull FloatingActionButton newPost, @NonNull ViewPager2 viewPager2) {
    this.rootView = rootView;
    this.bottomNavigation = bottomNavigation;
    this.mainActivity = mainActivity;
    this.newPost = newPost;
    this.viewPager2 = viewPager2;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      RelativeLayout mainActivity = (RelativeLayout) rootView;

      id = R.id.new_post;
      FloatingActionButton newPost = ViewBindings.findChildViewById(rootView, id);
      if (newPost == null) {
        break missingId;
      }

      id = R.id.view_pager_2;
      ViewPager2 viewPager2 = ViewBindings.findChildViewById(rootView, id);
      if (viewPager2 == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, bottomNavigation, mainActivity,
          newPost, viewPager2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
