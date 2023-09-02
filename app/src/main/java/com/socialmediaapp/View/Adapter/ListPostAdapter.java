package com.socialmediaapp.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.Post;
import com.socialmediaapp.Model.User;
import com.socialmediaapp.R;
import com.socialmediaapp.Socket.SocketIO;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Activity.CommentActivity;
import com.socialmediaapp.View.Activity.DetailsActivity;
import com.socialmediaapp.ViewModel.PostViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListPostAdapter extends RecyclerView.Adapter<ListPostAdapter.ListPostViewHolder> {
    List<Post> listPost;
    Context context;
    PostViewModel postViewModel;
    public ListPostAdapter(Context context, List<Post> listPost, PostViewModel postViewModel){
        this.context = context;
        this.listPost = listPost;
        this.postViewModel = postViewModel;
    }
    public static class ListPostViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_username;
        private final TextView tv_content;
        private final TextView tv_time;
        private final ImageView im_post;
        TextView numLikes;
        CircleImageView user_avatar;
        ImageView img_heart;
        ImageView imv_comment;
        Boolean myLike;
        public ListPostViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_content = itemView.findViewById(R.id.title_post);
            tv_time = itemView.findViewById(R.id.tv_time);
            im_post = itemView.findViewById(R.id.image_post);
            user_avatar = itemView.findViewById(R.id.user_avatar);
            imv_comment = itemView.findViewById(R.id.imv_comment);
            img_heart = itemView.findViewById(R.id.img_heart);
            numLikes = itemView.findViewById(R.id.numLikes);
            myLike = false;
        }
    }
    @NonNull
    @Override
    public ListPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_post, parent, false);
        return new ListPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPostViewHolder holder, int position) {
        holder.myLike=false;
        if (listPost == null) return;
        if(listPost.get(position).getLikeList()!=null){
            for(User u : listPost.get(position).getLikeList()){
                if(u.getUserId().equals(UserLocalData.getInstance(context).getUser().getId()))
                {
                    holder.img_heart.setImageResource(R.drawable.ic_favorite_heart);
                    holder.myLike = true;
                }

            }
        }
        holder.numLikes.setText(String.valueOf(listPost.get(position).getLikeList().size()));
        if(holder.numLikes.getText().equals("0")){
            holder.img_heart.setImageResource(R.drawable.ic_heart);
            holder.myLike = false;
        }
        holder.img_heart.setOnClickListener(click->{
            if(!holder.myLike){
                holder.img_heart.setImageResource(R.drawable.ic_favorite_heart);
                postViewModel.ToggleLike(listPost.get(position).getId());
                holder.myLike = true;
                holder.numLikes.setText(String.valueOf(Integer.parseInt(holder.numLikes.getText().toString())+1));
                new_Notification(listPost.get(position).getUserID());
            }
            else {
                holder.img_heart.setImageResource(R.drawable.ic_heart);
                postViewModel.ToggleLike(listPost.get(position).getId());
                holder.myLike = false;
                holder.numLikes.setText(String.valueOf(Integer.parseInt(holder.numLikes.getText().toString())-1));
            }
        });
        holder.imv_comment.setOnClickListener(click->{
            Intent intent = new Intent(context, CommentActivity.class);
            intent.putExtra("username",listPost.get(position).getUsername());
            intent.putExtra("avatar",listPost.get(position).getAvatar());
            intent.putExtra("content",listPost.get(position).getContent());
            intent.putExtra("img_content",listPost.get(position).getImage());
            intent.putExtra("postId", listPost.get(position).getId());
            intent.putExtra("UID", listPost.get(position).getUserID());
            context.startActivity(intent);
        });
        Duration duration = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            ZonedDateTime timePublish = ZonedDateTime.parse(listPost.get(position).getDatePublished());

            duration = Duration.between(timePublish, currentTime);

        }
        holder.tv_username.setText(listPost.get(position).getUsername());
        holder.tv_content.setText(listPost.get(position).getContent());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String time;
            if(duration.toDays() == 0){
                if(duration.toHours() == 0){
                    time = String.valueOf(duration.toMinutes()) + " phút trước";
                } else{
                    time = String.valueOf(duration.toHours()) + " giờ trước";
                }
            } else{
                time = String.valueOf(duration.toDays() + " ngày trước");
            }
            holder.tv_time.setText(time);
        }
        Glide.with(context).load(listPost.get(position).getImage()).into(holder.im_post);
        Glide.with(context).load(listPost.get(position).getAvatar()).into(holder.user_avatar);

        holder.user_avatar.setOnClickListener(click->{
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("fr", 1);
            intent.putExtra("UID", listPost.get(position).getUserID());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(listPost!=null){
            return listPost.size();
        }
        return 0;
    }
    public void new_Notification(String receiverID){
        SocketIO.join.connect();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("sender_id", UserLocalData.getInstance(context).getUser().getId());
            jsonObject.put("receiver_id",receiverID);
            jsonObject.put("content", UserLocalData.getInstance(context).getUser().getUsername()+" đã tim bài viết của bạn");
            SocketIO.join.emit("new notification", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
