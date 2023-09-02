package com.socialmediaapp.Repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.socialmediaapp.Model.Comment;
import com.socialmediaapp.Model.Post;
import com.socialmediaapp.Response.APIResponse;
import com.socialmediaapp.Response.NewPostResponse;
import com.socialmediaapp.Response.PostResponse;
import com.socialmediaapp.Services.BaseAPIService;
import com.socialmediaapp.Services.IPost;
import com.socialmediaapp.Utils.UserLocalData;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {


    private MutableLiveData<List<Post>> posts;


    private MutableLiveData<List<Post>> my_posts;


    private MutableLiveData<List<Comment>> comments;
    private MutableLiveData<String> message; // toast messages
    private MutableLiveData<Boolean> status; // proccess status for navigation
    private final Context context;
    public PostRepository(Context context){
        this.context = context;
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
        posts = new MutableLiveData<>();
        my_posts = new MutableLiveData<>();
        comments = new MutableLiveData<>();
    }
    public MutableLiveData<List<Comment>> getComments() {
        return comments;
    }
    public MutableLiveData<List<Post>> getMy_posts() {
        return my_posts;
    }

    public MutableLiveData<List<Post>> getPosts() {
        return posts;
    }
    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }
    public void getListPost(){
            BaseAPIService.createService(IPost.class).getListPost().enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(@NonNull Call<PostResponse> call, @NonNull Response<PostResponse> response) {
                    if (response.body() != null && response.body().getStatus().equals("success")){
                        posts.setValue( response.body().getListPost());
                        status.setValue(true);
                        message.setValue(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PostResponse> call, @NonNull Throwable t) {
                    status.setValue(false);
                    message.setValue("");
                }
            });
    }
    public void getPostsbyUID(String userID){
        BaseAPIService.createService(IPost.class).getPostsbyUID(userID).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(@NonNull Call<PostResponse> call, @NonNull Response<PostResponse> response) {
                if (response.body() != null && response.body().getStatus().equals("success")){
                    my_posts.setValue(response.body().getListPost());
                    status.setValue(true);
                    message.setValue(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostResponse> call, @NonNull Throwable t) {
                status.setValue(false);
                message.setValue("");
            }
        });
    }


    public void createPost(RequestBody requestPostContent, RequestBody requestUserId, MultipartBody.Part partbodyavatar){
        BaseAPIService.createService(IPost.class).createPost(requestPostContent, requestUserId, partbodyavatar).enqueue(new Callback<NewPostResponse>() {
            @Override
            public void onResponse(Call<NewPostResponse> call, Response<NewPostResponse> response) {
                getListPost();
            }

            @Override
            public void onFailure(Call<NewPostResponse> call, Throwable t) {
                Log.e("Post", "fail");
            }
        });
    }
    public void Togglelike(String postID){
        BaseAPIService.createService(IPost.class).togglelike(postID, UserLocalData.getInstance(context).getUser().getId()).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
    public void getComments(String postId){
        BaseAPIService.createService(IPost.class).getComments(postId).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if(response.body()!=null && response.body().getStatus().equals("success") && response.body().getComments()!=null){
                    comments.setValue(response.body().getComments());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
            }
        });
    }
    public void addComment(String postId, String content){
        BaseAPIService.createService(IPost.class).addComment(UserLocalData.getInstance(context).getUser().getId(),
                postId,
                UserLocalData.getInstance(context).getUser().getUsername(),
                content).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }
}
