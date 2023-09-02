package com.socialmediaapp.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.Comment;
import com.socialmediaapp.R;
import com.socialmediaapp.Utils.UserLocalData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context context;
    private List<Comment> comment;
    public CommentAdapter(Context context, List<Comment> comment){
        this.context = context;
        this.comment = comment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        if(comment.get(position)==null) return;
        else {
            if(!UserLocalData.getInstance(context).getUser().getAvatar().equals("")){
                Glide.with(context).load(comment.get(position).getAvatar()).into(holder.profileImage);
            }
            holder.tv_username.setText(comment.get(position).getUsername());
            holder.tv_comment.setText(comment.get(position).getContent());
        }
    }

    @Override
    public int getItemCount(){
        if (comment!=null)
            return comment.size();
        return 0;
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_username;
        CircleImageView profileImage;
        TextView tv_comment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            profileImage = itemView.findViewById(R.id.user_avatar);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }
    }

}
