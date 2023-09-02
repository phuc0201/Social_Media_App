package com.socialmediaapp.View.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.socialmediaapp.Model.User;
import com.socialmediaapp.R;
import com.socialmediaapp.Response.APIResponse;
import com.socialmediaapp.Response.UserResponse;
import com.socialmediaapp.Services.BaseAPIService;
import com.socialmediaapp.Services.IUserprofile;
import com.socialmediaapp.Utils.Permissions;
import com.socialmediaapp.Utils.RealPathUtil;
import com.socialmediaapp.Utils.UserLocalData;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {

    public SettingFragment() {}
    TextView edt_username;
    TextView edt_email;
    TextView edt_password;
    TextView edt_birthdate;
    CircleImageView civ_user_avatar;
    CircleImageView btn_chooseImage;
    AppCompatButton btn_logout;
    AppCompatButton btn_save;
    private Uri mUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        edt_username = view.findViewById(R.id.edt_username);
        edt_email = view.findViewById(R.id.edt_email);
        edt_password = view.findViewById(R.id.edt_password);
        edt_birthdate = view.findViewById(R.id.edt_birthdate);
        civ_user_avatar = view.findViewById(R.id.civ_user_avatar);
        btn_chooseImage = view.findViewById(R.id.btn_chooseImage);
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_save = view.findViewById(R.id.btn_update_profile);
        User user = UserLocalData.getInstance(getContext()).getUser();
        edt_username.setText(user.getUsername());
        edt_email.setText(user.getEmail());
        edt_birthdate.setText(user.getBirthdate());
        Glide.with(this).load(user.getAvatar()).into(civ_user_avatar);
        btn_chooseImage.setOnClickListener(click->{
            CheckPermission();
        });
        btn_logout.setOnClickListener(click->{
            UserLocalData.getInstance(getContext()).logout();
        });
        btn_save.setOnClickListener(click->{
            if(mUri!=null){
                String userId = UserLocalData.getInstance(requireContext()).getUser().getId();
                RequestBody requestUserId = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
                String IMAGE_PATH = RealPathUtil.getRealPath(requireContext(), mUri);
                File file = new File(IMAGE_PATH);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part partbodyavatar = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                BaseAPIService.createService(IUserprofile.class).updateavatar(requestUserId, partbodyavatar).enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                        if(response.body() != null && response.body().getStatus().equals("success")){
                            Toast.makeText(requireActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            UserLocalData.getInstance(requireContext()).updateAvatar(response.body().getUser().getAvatar());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
            }
            BaseAPIService.createService(IUserprofile.class).updateprofile(
                    user.getId(),
                    edt_username.getText().toString(),
                    edt_email.getText().toString(),
                    edt_birthdate.getText().toString()
            ).enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                    if(response.body()!=null){
                        if (response.body().getStatus().equals("success")){
                            Toast.makeText(requireActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            UserLocalData.getInstance(requireActivity()).updateProfile(new User("",edt_username.getText().toString(),
                                    edt_email.getText().toString(),
                                    edt_birthdate.getText().toString(),
                                    "",""));
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                    Toast.makeText(requireActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });

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
            requireActivity().requestPermissions(Permissions.permissions(), 100);
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
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                            civ_user_avatar.setImageBitmap(bitmap);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}