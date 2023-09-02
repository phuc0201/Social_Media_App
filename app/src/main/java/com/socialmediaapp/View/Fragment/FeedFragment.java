package com.socialmediaapp.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.socialmediaapp.R;
import com.socialmediaapp.View.Activity.DetailsActivity;
import com.socialmediaapp.View.Adapter.ListPostAdapter;
import com.socialmediaapp.ViewModel.PostViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FeedFragment extends Fragment{
    PostViewModel postViewModel;
    View view;
    ImageView chat_page;
    RecyclerView recyclerView;
    public FeedFragment(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = view.findViewById(R.id.list_post);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadData();
        chat_page = view.findViewById(R.id.chat_page);
        chat_page.setOnClickListener(click->{
            Intent intent = new Intent(requireActivity(), DetailsActivity.class);
            intent.putExtra("fr", 0);
            requireActivity().startActivity(intent);
        });
        return view;
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public void loadData(){
        postViewModel.getListPost().observe(getViewLifecycleOwner(),posts -> {
            if(posts!=null){
                ListPostAdapter listPostAdapter = new ListPostAdapter(getActivity(), posts, postViewModel);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setAdapter(listPostAdapter);
                listPostAdapter.notifyDataSetChanged();
            }
            else Toast.makeText(requireActivity(), "error", Toast.LENGTH_SHORT).show();
        });
    }

}