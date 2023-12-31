// Generated by view binder compiler. Do not edit!
package com.socialmediaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.socialmediaapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentContactBinding implements ViewBinding {
  @NonNull
  private final SwipeRefreshLayout rootView;

  @NonNull
  public final ImageView btnSearch;

  @NonNull
  public final RelativeLayout card1;

  @NonNull
  public final SwipeRefreshLayout chatPage;

  @NonNull
  public final EditText edtSearch;

  @NonNull
  public final AppBarLayout layoutGroupChat;

  @NonNull
  public final RecyclerView rcvListContact;

  @NonNull
  public final TextView tvUsername;

  private FragmentContactBinding(@NonNull SwipeRefreshLayout rootView, @NonNull ImageView btnSearch,
      @NonNull RelativeLayout card1, @NonNull SwipeRefreshLayout chatPage,
      @NonNull EditText edtSearch, @NonNull AppBarLayout layoutGroupChat,
      @NonNull RecyclerView rcvListContact, @NonNull TextView tvUsername) {
    this.rootView = rootView;
    this.btnSearch = btnSearch;
    this.card1 = card1;
    this.chatPage = chatPage;
    this.edtSearch = edtSearch;
    this.layoutGroupChat = layoutGroupChat;
    this.rcvListContact = rcvListContact;
    this.tvUsername = tvUsername;
  }

  @Override
  @NonNull
  public SwipeRefreshLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentContactBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentContactBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_contact, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentContactBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_search;
      ImageView btnSearch = ViewBindings.findChildViewById(rootView, id);
      if (btnSearch == null) {
        break missingId;
      }

      id = R.id.card1;
      RelativeLayout card1 = ViewBindings.findChildViewById(rootView, id);
      if (card1 == null) {
        break missingId;
      }

      SwipeRefreshLayout chatPage = (SwipeRefreshLayout) rootView;

      id = R.id.edt_search;
      EditText edtSearch = ViewBindings.findChildViewById(rootView, id);
      if (edtSearch == null) {
        break missingId;
      }

      id = R.id.layout_group_chat;
      AppBarLayout layoutGroupChat = ViewBindings.findChildViewById(rootView, id);
      if (layoutGroupChat == null) {
        break missingId;
      }

      id = R.id.rcv_list_contact;
      RecyclerView rcvListContact = ViewBindings.findChildViewById(rootView, id);
      if (rcvListContact == null) {
        break missingId;
      }

      id = R.id.tv_username;
      TextView tvUsername = ViewBindings.findChildViewById(rootView, id);
      if (tvUsername == null) {
        break missingId;
      }

      return new FragmentContactBinding((SwipeRefreshLayout) rootView, btnSearch, card1, chatPage,
          edtSearch, layoutGroupChat, rcvListContact, tvUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
