package com.socialmediaapp.View.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.socialmediaapp.Model.Notification;
import com.socialmediaapp.R;
import com.socialmediaapp.Socket.SocketIO;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Adapter.NotificationAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.emitter.Emitter;

public class NotificationFragment extends Fragment {
    RecyclerView rcv_notification;
    List<Notification> notifications;

    public NotificationFragment(){}
    public void init(View view){
        rcv_notification = view.findViewById(R.id.rcv_notification);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        init(view);
        SocketIO.join.connect();
        SocketIO.join.on("new notification", newNotification);
        notifications = new ArrayList<>();
        rcv_notification.setLayoutManager(new LinearLayoutManager(getContext()));
        loadData();
        return view;
    }
    Emitter.Listener newNotification = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            requireActivity().runOnUiThread(()->{
                JSONObject new_notification = (JSONObject) args[0];
                try {
                   if(new_notification.get("receiver_id").equals(UserLocalData.getInstance(getContext()).getUser().getId())){
                       notifications.add(new Notification(
                               new_notification.getString("UID"),
                               new_notification.getString("avatar"),
                               new_notification.getString("content")
                       ));
                       loadData();
                   }
                }catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(requireActivity().isFinishing()){
            SocketIO.join.off("new notification", newNotification);
            SocketIO.join.disconnect();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadData(){
        NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), notifications);
        rcv_notification.setAdapter(notificationAdapter);
        notificationAdapter.notifyDataSetChanged();
    }
}