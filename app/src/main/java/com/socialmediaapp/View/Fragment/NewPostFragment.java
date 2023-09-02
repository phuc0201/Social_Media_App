package com.socialmediaapp.View.Fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.socialmediaapp.R;
import com.socialmediaapp.Utils.Permissions;
import com.socialmediaapp.Utils.RealPathUtil;
import com.socialmediaapp.Utils.UserLocalData;
import com.socialmediaapp.View.Activity.MainActivity;
import com.socialmediaapp.ViewModel.PostViewModel;

import java.io.File;
import java.io.IOException;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@AndroidEntryPoint
public class NewPostFragment extends Fragment {
    public static final  int MY_REQUEST_CODE = 1000;
    public static  final String TAG = MainActivity.class.getName();
    PostViewModel postViewModel;
    ImageButton btn_upload_image;
    AppCompatButton btn_create_new_post;
    CircleImageView profileImage;
    TextView tv_username;

    EditText ed_content;
    ImageView im_image;
    FloatingActionButton im_submit;
    private Uri mUri;

    public void initUI(View view){
        btn_upload_image = view.findViewById(R.id.btn_upload_image);
        ed_content = view.findViewById(R.id.post_content);
        im_image = view.findViewById(R.id.imv_new_post);
        btn_create_new_post = view.findViewById(R.id.btn_create_new_post);
        profileImage = view.findViewById(R.id.profileImage);
        tv_username = view.findViewById(R.id.tv_username);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);
        initUI(view);
        Glide.with(view).load(UserLocalData.getInstance(requireActivity()).getUser().getAvatar()).into(profileImage);
        tv_username.setText(UserLocalData.getInstance(getContext()).getUser().getUsername());
        ed_content.setOnClickListener(click->{
            checkKeyBoardDisplay(view);
        });
        ed_content.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                checkKeyBoardDisplay(view);
            }
        });
        btn_upload_image.setOnClickListener(click->{
            CheckPermission();
        });
        btn_create_new_post.setOnClickListener(click->{
            if(mUri != null){
                String userId = UserLocalData.getInstance(requireContext()).getUser().getId();
                RequestBody requestUserId = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
                RequestBody requestContent = RequestBody.create(MediaType.parse("multipart/form-data"), ed_content.getText().toString());
                String IMAGE_PATH = RealPathUtil.getRealPath(requireContext(), mUri);
                File file = new File(IMAGE_PATH);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part partbodyavatar = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                postViewModel.createPost(requestContent, requestUserId, partbodyavatar);
                ed_content.setText("");
                im_image.setImageResource(0);
            }
        });
        return view;
    }
    private void CheckPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (requireActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else  {
            requireActivity().requestPermissions(Permissions.permissions(), MY_REQUEST_CODE);
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                            im_image.setImageBitmap(bitmap);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

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