// Generated by view binder compiler. Do not edit!
package com.socialmediaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.socialmediaapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MessageOtherBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView civUserAvatar;

  @NonNull
  public final ImageView imageChatProfileOther;

  @NonNull
  public final LinearLayout layoutChatContainerOther;

  @NonNull
  public final LinearLayout lnChatMessageOther;

  @NonNull
  public final ConstraintLayout mainLayoutChatMessageOther;

  @NonNull
  public final TextView textChatMessageOther;

  @NonNull
  public final TextView textChatTimestampOther;

  @NonNull
  public final TextView textChatUserOther;

  private MessageOtherBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleImageView civUserAvatar, @NonNull ImageView imageChatProfileOther,
      @NonNull LinearLayout layoutChatContainerOther, @NonNull LinearLayout lnChatMessageOther,
      @NonNull ConstraintLayout mainLayoutChatMessageOther, @NonNull TextView textChatMessageOther,
      @NonNull TextView textChatTimestampOther, @NonNull TextView textChatUserOther) {
    this.rootView = rootView;
    this.civUserAvatar = civUserAvatar;
    this.imageChatProfileOther = imageChatProfileOther;
    this.layoutChatContainerOther = layoutChatContainerOther;
    this.lnChatMessageOther = lnChatMessageOther;
    this.mainLayoutChatMessageOther = mainLayoutChatMessageOther;
    this.textChatMessageOther = textChatMessageOther;
    this.textChatTimestampOther = textChatTimestampOther;
    this.textChatUserOther = textChatUserOther;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MessageOtherBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MessageOtherBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.message_other, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MessageOtherBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.civ_user_avatar;
      CircleImageView civUserAvatar = ViewBindings.findChildViewById(rootView, id);
      if (civUserAvatar == null) {
        break missingId;
      }

      id = R.id.image_chat_profile_other;
      ImageView imageChatProfileOther = ViewBindings.findChildViewById(rootView, id);
      if (imageChatProfileOther == null) {
        break missingId;
      }

      id = R.id.layout_chat_container_other;
      LinearLayout layoutChatContainerOther = ViewBindings.findChildViewById(rootView, id);
      if (layoutChatContainerOther == null) {
        break missingId;
      }

      id = R.id.ln_chat_message_other;
      LinearLayout lnChatMessageOther = ViewBindings.findChildViewById(rootView, id);
      if (lnChatMessageOther == null) {
        break missingId;
      }

      ConstraintLayout mainLayoutChatMessageOther = (ConstraintLayout) rootView;

      id = R.id.text_chat_message_other;
      TextView textChatMessageOther = ViewBindings.findChildViewById(rootView, id);
      if (textChatMessageOther == null) {
        break missingId;
      }

      id = R.id.text_chat_timestamp_other;
      TextView textChatTimestampOther = ViewBindings.findChildViewById(rootView, id);
      if (textChatTimestampOther == null) {
        break missingId;
      }

      id = R.id.text_chat_user_other;
      TextView textChatUserOther = ViewBindings.findChildViewById(rootView, id);
      if (textChatUserOther == null) {
        break missingId;
      }

      return new MessageOtherBinding((ConstraintLayout) rootView, civUserAvatar,
          imageChatProfileOther, layoutChatContainerOther, lnChatMessageOther,
          mainLayoutChatMessageOther, textChatMessageOther, textChatTimestampOther,
          textChatUserOther);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
