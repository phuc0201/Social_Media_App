package com.socialmediaapp.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.User;
import com.socialmediaapp.R;
import com.socialmediaapp.View.Activity.ChatActivity;

import java.util.List;

public class ListContactAdapter extends RecyclerView.Adapter<ListContactAdapter.ListContactViewHolder>{
    private final List<User> friends;
    private final Context context;
    public ListContactAdapter(Context context, List<User> friends){
        this.context = context;
        this.friends = friends;
    }
    @NonNull
    @Override
    public ListContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListContactViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ListContactViewHolder holder, int position) {
        if(friends.get(position)!=null)
        {
            holder.tv_username.setText(friends.get(position).getUsername());

            if(!friends.get(position).getAvatar().equals("")){
                Glide.with(context).load(friends.get(position).getAvatar()).into(holder.img_user_avatar);
            }

            holder.layout.setOnClickListener(view -> {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("UID", friends.get(position).getId());
                intent.putExtra("username", holder.tv_username.getText());
                intent.putExtra("avatar", friends.get(position).getAvatar());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        if(friends == null) return 0;
        return friends.size();
    }

    public static class ListContactViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layout;
        TextView tv_username;
        ImageView img_user_avatar;
//        CardView active_status;
        public ListContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            layout = itemView.findViewById(R.id.contact_item);
            img_user_avatar = itemView.findViewById(R.id.img_user_avatar);
        }
    }
}
