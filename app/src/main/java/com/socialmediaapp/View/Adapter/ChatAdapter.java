package com.socialmediaapp.View.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.Message;
import com.socialmediaapp.R;
import com.socialmediaapp.Utils.UserLocalData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter{
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context context;
    private List<Message> messages;
    private String avatar_user;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Message> messages, Context context, String avatar_user){
        this.messages = messages;
        this.context = context;
        this.avatar_user = avatar_user;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_other, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                try {
                    ((SentMessageHolder) holder).bind(message);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                try {
                    if(position + 1 < messages.size() && !messages.get(position+1).getSender_id().equals(message.getSender_id())
                     || position + 1 == messages.size()){
                        if(!avatar_user.equals(""))
                            Glide.with(context).load(avatar_user).into(((ReceivedMessageHolder) holder).civ_user_avatar);
                    }
                    if(position + 1 < messages.size() && messages.get(position+1).getSender_id().equals(message.getSender_id())){
                        Glide.with(context).load("").into(((ReceivedMessageHolder) holder).civ_user_avatar);
                    }
                    ((ReceivedMessageHolder) holder).bind(message);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
        }

    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        if (message.getSender_id().equals(UserLocalData.getInstance(context).getUser().getId())) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public int getItemCount() {
        if(messages!=null){
            return messages.size();
        }
        return 0;
    }
    private static class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView tv_message;
        CardView card_chat_message_me;
        TextView text_chat_timestamp_me;
        SentMessageHolder(View itemView) {
            super(itemView);
            card_chat_message_me = itemView.findViewById(R.id.card_chat_message_me);
            tv_message = (TextView) itemView.findViewById(R.id.text_chat_message_me);
            text_chat_timestamp_me = itemView.findViewById(R.id.text_chat_timestamp_me);
            card_chat_message_me.setOnClickListener(click->{
                text_chat_timestamp_me.setVisibility(text_chat_timestamp_me.getVisibility() == TextView.GONE ? TextView.VISIBLE : TextView.GONE);
            });
        }
        @SuppressLint("SimpleDateFormat")
        void bind(Message message) throws ParseException {
            tv_message.setText(message.getMessage());
            Instant instant3 = null;
            long timeStampSeconds_current = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                instant3 = Instant.now();
                timeStampSeconds_current = instant3.getEpochSecond();
            }
            DateFormat df = new SimpleDateFormat("dd MMM yyyy");
            Date current_date = df.parse(df.format(new Date(timeStampSeconds_current * 1000)));
            Date date = df.parse(df.format(new Date(Long.parseLong(message.getTimestamp()) * 1000)));
            DateFormat df_hour_minute = new SimpleDateFormat("hh:mm");;
            if(date!=null && date.before(current_date)){
                df_hour_minute = new SimpleDateFormat("dd MMM yyyy hh:mm");
            }
            assert date != null;
            text_chat_timestamp_me.setText(df_hour_minute.format(new Date(Long.parseLong(message.getTimestamp()) * 1000)));
        }
    }

    private static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView tv_message;
        TextView text_chat_timestamp_other;
        LinearLayout ln_chat_message_other;
        CircleImageView civ_user_avatar;
        ConstraintLayout main_layout_chat_message_other;
        ReceivedMessageHolder(View itemView) {
            super(itemView);
            civ_user_avatar = itemView.findViewById(R.id.civ_user_avatar);
            ln_chat_message_other = itemView.findViewById(R.id.ln_chat_message_other);
            tv_message = (TextView) itemView.findViewById(R.id.text_chat_message_other);
            text_chat_timestamp_other = itemView.findViewById(R.id.text_chat_timestamp_other);
            main_layout_chat_message_other = itemView.findViewById(R.id.main_layout_chat_message_other);
            ln_chat_message_other.setOnClickListener(click->{
                text_chat_timestamp_other.setVisibility(text_chat_timestamp_other.getVisibility() == TextView.GONE ? TextView.VISIBLE : TextView.GONE);
            });


        }
        @SuppressLint("SimpleDateFormat")
        void bind(Message message) throws ParseException {
            tv_message.setText(message.getMessage());
            Instant instant3 = null;
            long timeStampSeconds_current = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                instant3 = Instant.now();
                timeStampSeconds_current = instant3.getEpochSecond();
            }
            DateFormat df = new SimpleDateFormat("dd MMM yyyy");
            Date current_date = df.parse(df.format(new Date(timeStampSeconds_current * 1000)));
            Date date = df.parse(df.format(new Date(Long.parseLong(message.getTimestamp()) * 1000)));
            DateFormat df_hour_minute = new SimpleDateFormat("hh:mm");;
            if(date!=null && date.before(current_date)){
                df_hour_minute = new SimpleDateFormat("dd MMM yyyy hh:mm");
            }
            assert date != null;
            text_chat_timestamp_other.setText(df_hour_minute.format(new Date(Long.parseLong(message.getTimestamp()) * 1000)));
        }
    }
}
