// Generated by view binder compiler. Do not edit!
package com.socialmediaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.socialmediaapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemNotificationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CircleImageView ProfileImage;

  @NonNull
  public final TextView newNotification;

  @NonNull
  public final LinearLayout r1;

  @NonNull
  public final TextView tvContent;

  @NonNull
  public final TextView tvUsername;

  private ItemNotificationBinding(@NonNull LinearLayout rootView,
      @NonNull CircleImageView ProfileImage, @NonNull TextView newNotification,
      @NonNull LinearLayout r1, @NonNull TextView tvContent, @NonNull TextView tvUsername) {
    this.rootView = rootView;
    this.ProfileImage = ProfileImage;
    this.newNotification = newNotification;
    this.r1 = r1;
    this.tvContent = tvContent;
    this.tvUsername = tvUsername;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemNotificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemNotificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_notification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemNotificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Profile_Image;
      CircleImageView ProfileImage = ViewBindings.findChildViewById(rootView, id);
      if (ProfileImage == null) {
        break missingId;
      }

      id = R.id.newNotification;
      TextView newNotification = ViewBindings.findChildViewById(rootView, id);
      if (newNotification == null) {
        break missingId;
      }

      id = R.id.r1;
      LinearLayout r1 = ViewBindings.findChildViewById(rootView, id);
      if (r1 == null) {
        break missingId;
      }

      id = R.id.tv_content;
      TextView tvContent = ViewBindings.findChildViewById(rootView, id);
      if (tvContent == null) {
        break missingId;
      }

      id = R.id.tv_username;
      TextView tvUsername = ViewBindings.findChildViewById(rootView, id);
      if (tvUsername == null) {
        break missingId;
      }

      return new ItemNotificationBinding((LinearLayout) rootView, ProfileImage, newNotification, r1,
          tvContent, tvUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
