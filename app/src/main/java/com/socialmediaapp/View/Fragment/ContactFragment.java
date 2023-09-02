package com.socialmediaapp.View.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.socialmediaapp.Model.User;
import com.socialmediaapp.R;
import com.socialmediaapp.Socket.SocketIO;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Adapter.ListContactAdapter;
import com.socialmediaapp.ViewModel.ContactViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.socket.emitter.Emitter;

@AndroidEntryPoint
public class ContactFragment extends Fragment {

    ContactViewModel contactViewModel;
    RecyclerView recyclerView;
    LinearLayout contact_item;
    List<User> list_contact;
    ListContactAdapter listConversationAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    View view;
    EditText edt_search;
    public ContactFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        edt_search = view.findViewById(R.id.edt_search);
        recyclerView = view.findViewById(R.id.rcv_list_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list_contact = new ArrayList<>();
        SocketIO.join.connect();
        SocketIO.join.emit("get list contact", UserLocalData.getInstance(getActivity()).getUser().getId());
        SocketIO.join.on("list contact", getListContact);

        swipeRefreshLayout = view.findViewById(R.id.chat_page);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            SocketIO.join.emit("get list contact", UserLocalData.getInstance(getActivity()).getUser().getId());
            fetchTimelineAsync(2000);
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, R.color.colorAccent, R.color.colorPrimaryDark);


        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterContact(edt_search.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    Emitter.Listener getListContact = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            requireActivity().runOnUiThread(()->{
                JSONObject lst_contact = (JSONObject) args[0];
                try {
                    if(lst_contact.get("userID").equals(UserLocalData.getInstance(requireActivity()).getUser().getId())){
                        Gson g = new Gson();
                        list_contact.clear();
                        for(int index = 0; index < lst_contact.getJSONArray("friendlist").length(); index++){
                            list_contact.add(g.fromJson(lst_contact.getJSONArray("friendlist").get(index).toString(), User.class));
                        }
                        loadListContact(list_contact);
                    }
                }catch (JSONException e){
                    throw new RuntimeException(e);
                }
            });
        }
    };

    @SuppressLint("NotifyDataSetChanged")
    public void loadListContact(List<User> friends){
        listConversationAdapter = new ListContactAdapter(requireActivity(), friends);
        recyclerView.setAdapter(listConversationAdapter);
        listConversationAdapter.notifyDataSetChanged();
    }
    public void fetchTimelineAsync(int time){
        Handler handler = new Handler();
        loadListContact(list_contact);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, time);
    }
    private void filterContact(String username){
        List<User> friends = new ArrayList<>();
        for(User u: list_contact){
            if(u.getUsername().toLowerCase().contains(username.toLowerCase())){
                friends.add(u);
            }
        }
        loadListContact(friends);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SocketIO.join.off("list contact", getListContact);
    }
}