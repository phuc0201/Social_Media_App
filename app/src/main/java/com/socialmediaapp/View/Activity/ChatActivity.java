package com.socialmediaapp.View.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.Message;
import com.socialmediaapp.R;
import com.socialmediaapp.Socket.SocketIO;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Adapter.ChatAdapter;
import com.socialmediaapp.ViewModel.ChatViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.socket.emitter.Emitter;

@AndroidEntryPoint
public class ChatActivity extends AppCompatActivity {
    private ChatViewModel chatViewModel;
    private TextView tv_username;
    private RecyclerView rcv_message;
    private ChatAdapter chatAdapter;
    private ImageView imv_Send;
    private ImageView img_avatar_user;
    private EditText edt_message;
    private List<Message> messageList;
    private String avatar_user = "";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        SocketIO.join.connect();
        SocketIO.join.on("new message", onNewMessage);


        imv_Send = findViewById(R.id.imv_chat_send);
        edt_message = findViewById(R.id.edit_chat_message);
        rcv_message = findViewById(R.id.recycler_chat);
        tv_username = findViewById(R.id.tv_username);
        img_avatar_user = findViewById(R.id.img_avatar_user);
        chatAdapter = new ChatAdapter();

        intent = getIntent();
        avatar_user = intent.getStringExtra("avatar");
        tv_username.setText(intent.getStringExtra("username"));
        if(!avatar_user.equals("")){
            Glide.with(this).load(avatar_user).into(img_avatar_user);
        }
        imv_Send.setOnClickListener(send->{
            send_message();
        });

        edt_message.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus){
                checkKeyBoardDisplay();
            }
        });
        edt_message.setOnClickListener(click->{
            checkKeyBoardDisplay();
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_message.setLayoutManager(layoutManager);
        chatViewModel.getMessages(intent.getStringExtra("UID")).observe(this, messages->{
            messageList = messages;
            chatAdapter.setData(messages, this, avatar_user);
            rcv_message.setAdapter(chatAdapter);
            rcv_message.scrollToPosition(messages.size()-1);
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        messageList.clear();
        chatAdapter.setData(messageList, ChatActivity.this, avatar_user);
        finish();
    }


    public void send_message(){
        Instant instant3 = null;
        long timeStampSeconds = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            instant3 = Instant.now();
            timeStampSeconds = instant3.getEpochSecond();
        }
        if(!edt_message.getText().toString().trim().equals("")){
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("sender_id", UserLocalData.getInstance(this).getUser().getId());
                jsonObject.put("chat_id", chatViewModel.getChat_id().getValue());
                jsonObject.put("message", edt_message.getText());
                jsonObject.put("timestamp", Long.toString(timeStampSeconds));
                jsonObject.put("type", "text");
                SocketIO.join.emit("new message", jsonObject);
                edt_message.setText("");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                JSONObject new_message = (JSONObject) args[0];
                try {
                    if(new_message.getString("chat_id").equals(chatViewModel.getChat_id().getValue())) {
                        messageList.add(new Message(
                                new_message.getString("sender_id"),
                                new_message.getString("message"),
                                new_message.getString("timestamp"),
                                new_message.getString("type")
                        ));
                        chatAdapter.setData(messageList, ChatActivity.this, avatar_user);
                        if(messageList.size() > 0){
                            rcv_message.scrollToPosition(messageList.size()-1);
                        }

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    };



    private void checkKeyBoardDisplay(){
        final View activityRootView = findViewById(R.id.main_layout_chat_activity);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = activityRootView.getRootView().getHeight();
                int keypadHeight = screenHeight - r.height();
                if (keypadHeight > screenHeight * 0.15) {
                    if(messageList.size()>0){
                        rcv_message.scrollToPosition(messageList.size()-1);
                        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }
}