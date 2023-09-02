package com.socialmediaapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.socialmediaapp.Model.Comment;
import com.socialmediaapp.Model.Post;
import com.socialmediaapp.Repository.PostRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@HiltViewModel
public class PostViewModel extends ViewModel {
    private final PostRepository postRepo;
    private final MutableLiveData<String> message;
    private final MutableLiveData<Boolean> status;
    private final MutableLiveData<List<Post>> listPost;


    private final MutableLiveData<List<Comment>> comments;

    private final MutableLiveData<List<Post>> listpost_byUID;
    @Inject
    public PostViewModel(PostRepository postRepo) {
        this.postRepo = postRepo;
        message = postRepo.getMessage();
        status = postRepo.getStatus();
        listPost = postRepo.getPosts();
        listpost_byUID = postRepo.getMy_posts();
        comments = postRepo.getComments();
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public MutableLiveData<List<Post>> getListPost() {
        postRepo.getListPost();
        return listPost;
    }
    public PostRepository getPostRepo() {
        return postRepo;
    }


    public void createPost(RequestBody requestPostContent, RequestBody requestUserId, MultipartBody.Part partbodyavatar){
        postRepo.createPost(requestPostContent, requestUserId, partbodyavatar);
    }
    public void ToggleLike(String PostID){
        postRepo.Togglelike(PostID);
    }
    public MutableLiveData<List<Comment>> getComments(String postId){
        postRepo.getComments(postId);
        return comments;
    }
    public void addComment(String postId, String content){
        postRepo.addComment(postId, content);
    }

    public MutableLiveData<List<Post>> getPostsbyUID(String userID) {
        postRepo.getPostsbyUID(userID);
        return listpost_byUID;
    }
}
