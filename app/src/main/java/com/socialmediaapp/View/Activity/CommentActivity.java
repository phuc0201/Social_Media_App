package com.socialmediaapp.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.Comment;
import com.socialmediaapp.R;
import com.socialmediaapp.Socket.SocketIO;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Adapter.CommentAdapter;
import com.socialmediaapp.ViewModel.PostViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;
@AndroidEntryPoint
public class CommentActivity extends AppCompatActivity {

    CircleImageView user_avatar;
    TextView tv_username;
    TextView title_post;
    ImageView image_post;
    RecyclerView rcv_comment;
    ImageView imv_send_comment;
    EditText edit_comment;
    PostViewModel postViewModel;
    List<Comment> comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        setContentView(R.layout.activity_comment);
        rcv_comment = findViewById(R.id.rcv_comment);
        Intent intent = getIntent();
        user_avatar = findViewById(R.id.user_avatar);
        tv_username = findViewById(R.id.tv_username);
        title_post = findViewById(R.id.title_post);
        image_post = findViewById(R.id.image_post);
        edit_comment = findViewById(R.id.edit_comment);
        imv_send_comment = findViewById(R.id.imv_send_comment);
        comments = new ArrayList<>();
        Glide.with(this).load(intent.getStringExtra("avatar")).into(user_avatar);
        tv_username.setText(intent.getStringExtra("username"));
        title_post.setText(intent.getStringExtra("content"));
        Glide.with(this).load(intent.getStringExtra("img_content")).into(image_post);
        postViewModel.getComments(intent.getStringExtra("postId")).observe(this, commentList -> {
            if(commentList.size()>0)
            {
                load(commentList);
                comments = commentList;
            }
        });
        imv_send_comment.setOnClickListener(click->{
            if(!edit_comment.getText().toString().trim().equals("")){
                comments.add(new Comment(UserLocalData.getInstance(this).getUser().getId(),
                        UserLocalData.getInstance(this).getUser().getUsername(),
                        edit_comment.getText().toString(),
                        (UserLocalData.getInstance(this).getUser().getAvatar())));
                postViewModel.addComment(intent.getStringExtra("postId"), edit_comment.getText().toString());
                load(comments);
                new_Notification(intent.getStringExtra("UID"));
                edit_comment.setText("");

            }
        });

    }
    public void load(List<Comment> listComment){
        rcv_comment.setLayoutManager(new LinearLayoutManager(this));
        CommentAdapter commentAdapter = new CommentAdapter(this, listComment);
        rcv_comment.setAdapter(commentAdapter);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        comments.clear();
    }
    public void new_Notification(String receiverID){
        SocketIO.join.connect();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("sender_id", UserLocalData.getInstance(this).getUser().getId());
            jsonObject.put("receiver_id",receiverID);
            jsonObject.put("content", UserLocalData.getInstance(this).getUser().getUsername()+" đã bình luận vào bài viết của bạn");
            SocketIO.join.emit("new notification", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}