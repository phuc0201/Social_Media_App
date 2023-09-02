package com.socialmediaapp.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.User;
import com.socialmediaapp.R;
import com.socialmediaapp.Repository.ContactRespository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FindUserApdaper extends RecyclerView.Adapter<FindUserApdaper.FindUserViewHolder>{
    Context context;
    List<User> users;
    public FindUserApdaper(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public FindUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FindUserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_find_user,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FindUserViewHolder holder, int position) {
        if(users.get(position).getFriendship()){
            holder.btn_follow.setText("Followed");
        }else{
            holder.btn_follow.setText("Follow");
        }
        holder.tv_username.setText(users.get(position).getUsername());
        if(!users.get(position).getAvatar().equals("")){
            Glide.with(context).load(users.get(position).getAvatar()).into(holder.profileImage);
        }
        holder.btn_follow.setOnClickListener(click -> {
            ContactRespository contact = new ContactRespository(context);
            contact.addFriend(users.get(position).getId());
            if(holder.btn_follow.getText().equals("Follow")){
                holder.btn_follow.setText("Followed");
            }else{
                holder.btn_follow.setText("Follow");
            }
        });

    }

    @Override
    public int getItemCount() {
        if(users == null) return 0;
        return users.size();
    }

    public static class FindUserViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_username;
        CircleImageView profileImage;
        AppCompatButton btn_follow;
        public FindUserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            profileImage = itemView.findViewById(R.id.profileImage);
            btn_follow = itemView.findViewById(R.id.btn_follow);
        }
    }
}
