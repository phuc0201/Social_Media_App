package com.socialmediaapp.View.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.socialmediaapp.Model.User;
import com.socialmediaapp.R;
import com.socialmediaapp.View.Adapter.FindUserApdaper;
import com.socialmediaapp.ViewModel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class AddFriendFragment extends Fragment {

    RecyclerView recyclerView;
    ContactViewModel contactViewModel;
    EditText edt_search;
    List<User> userList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactViewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addfriend, container, false);
        edt_search = view.findViewById(R.id.edt_search);
        recyclerView = view.findViewById(R.id.rcv_list_user);
        userList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        contactViewModel.findUser().observe(getViewLifecycleOwner(), users -> {
            userList = users;
            loadListContact(users);
        });
        edt_search.setOnClickListener(click->{
            checkKeyBoardDisplay(view);
        });
        edt_search.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                checkKeyBoardDisplay(view);
            }
        });
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
    public void reloadData(){

    }
    public void loadListContact(List<User> users){
        FindUserApdaper findUserApdaper = new FindUserApdaper(getActivity(), users);
        recyclerView.setAdapter(findUserApdaper);
        findUserApdaper.notifyDataSetChanged();
    }
    private void filterContact(String username){
        List<User> friends = new ArrayList<>();
        for(User u: userList){
            if(u.getUsername().toLowerCase().contains(username.toLowerCase())){
                friends.add(u);
            }
        }
        loadListContact(friends);
    }
    private void checkKeyBoardDisplay(View view){
        final View activityRootView = requireActivity().findViewById(R.id.mainActivity);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = activityRootView.getRootView().getHeight();
                int keypadHeight = screenHeight - r.height();
                if (keypadHeight > screenHeight * 0.15) {
                    requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
                }
                else requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

            }
        });
    }
}