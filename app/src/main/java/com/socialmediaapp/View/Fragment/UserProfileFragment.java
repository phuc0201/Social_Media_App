package com.socialmediaapp.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.socialmediaapp.R;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Adapter.ListPostAdapter;
import com.socialmediaapp.ViewModel.ContactViewModel;
import com.socialmediaapp.ViewModel.PostViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class UserProfileFragment extends Fragment {

    private static final String ARG_PARAM_UID = "UID";

    private String UID="";
    RecyclerView rcv_list_post;
    PostViewModel postViewModel;
    ContactViewModel contactViewModel;
    TextView tv_numPosts;
    TextView tv_username;
    CircleImageView civ_user_avatar;
    TextView tv_numFriends;
    LinearLayout ll_contact;

    public static UserProfileFragment newInstance(String param1) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_UID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public void init(View view){
        rcv_list_post = view.findViewById(R.id.rcv_list_post);
        tv_numPosts = view.findViewById(R.id.tv_numPosts);
        tv_username = view.findViewById(R.id.tv_username);
        civ_user_avatar = view.findViewById(R.id.civ_user_avatar);
        tv_numFriends = view.findViewById(R.id.tv_numFriends);
        ll_contact = view.findViewById(R.id.ll_contact);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewModel=new ViewModelProvider(this).get(PostViewModel.class);
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        if (getArguments() != null) {
            UID = getArguments().getString(ARG_PARAM_UID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        init(view);
        if(UserLocalData.getInstance(getContext()).getUser().getId().equals(UID)){
            ll_contact.setVisibility(View.GONE);
        }
        rcv_list_post.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_list_post.setNestedScrollingEnabled(false);
        loadData();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().finish();
    }

    public void loadData(){
        contactViewModel.getListContact().observe(getViewLifecycleOwner(),friends->{
            if(friends!=null && friends.size()>0){
                tv_numFriends.setText(String.valueOf(friends.size()));
            }
        });
        postViewModel.getPostsbyUID(UID).observe(getViewLifecycleOwner(),posts -> {
            if(posts!=null && posts.size()>0){
                tv_username.setText(String.valueOf(posts.get(0).getUsername()));
                Glide.with(this).load(posts.get(0).getAvatar()).into(civ_user_avatar);
                tv_numPosts.setText(String.valueOf(posts.size()));
                ListPostAdapter listPostAdapter = new ListPostAdapter(getActivity(), posts, postViewModel);
                rcv_list_post.setNestedScrollingEnabled(false);
                rcv_list_post.setAdapter(listPostAdapter);
                listPostAdapter.notifyDataSetChanged();
            }
            else Toast.makeText(requireActivity(), "error", Toast.LENGTH_SHORT).show();
        });
    }
}