package com.socialmediaapp.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.Notification;
import com.socialmediaapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{
    Context context;
    List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NotificationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        if(notifications!=null){
            holder.tv_content.setText(notifications.get(position).getContent());
            Glide.with(context).load(notifications.get(position).getAvatar()).into(holder.Profile_Image);
        }
    }

    @Override
    public int getItemCount() {
        if(notifications!=null)
            return notifications.size();
        return 0;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{
        TextView tv_username;
        TextView tv_content;
        CircleImageView Profile_Image;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_content = itemView.findViewById(R.id.tv_content);;
            Profile_Image = itemView.findViewById(R.id.Profile_Image);
        }
    }
}
