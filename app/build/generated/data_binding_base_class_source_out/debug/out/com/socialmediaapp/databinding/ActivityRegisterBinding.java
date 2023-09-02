// Generated by view binder compiler. Do not edit!
package com.socialmediaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.socialmediaapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatButton btnBack;

  @NonNull
  public final AppCompatButton btnSignup;

  @NonNull
  public final EditText edTBirthdate;

  @NonNull
  public final EditText email;

  @NonNull
  public final EditText password;

  @NonNull
  public final RadioButton rdBGenderFemale;

  @NonNull
  public final RadioButton rdBGenderMale;

  @NonNull
  public final RadioGroup rdGGender;

  @NonNull
  public final TextView tvDatePickerActions;

  @NonNull
  public final TextView tvSignup;

  @NonNull
  public final EditText username;

  private ActivityRegisterBinding(@NonNull LinearLayout rootView, @NonNull AppCompatButton btnBack,
      @NonNull AppCompatButton btnSignup, @NonNull EditText edTBirthdate, @NonNull EditText email,
      @NonNull EditText password, @NonNull RadioButton rdBGenderFemale,
      @NonNull RadioButton rdBGenderMale, @NonNull RadioGroup rdGGender,
      @NonNull TextView tvDatePickerActions, @NonNull TextView tvSignup,
      @NonNull EditText username) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnSignup = btnSignup;
    this.edTBirthdate = edTBirthdate;
    this.email = email;
    this.password = password;
    this.rdBGenderFemale = rdBGenderFemale;
    this.rdBGenderMale = rdBGenderMale;
    this.rdGGender = rdGGender;
    this.tvDatePickerActions = tvDatePickerActions;
    this.tvSignup = tvSignup;
    this.username = username;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_back;
      AppCompatButton btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btn_signup;
      AppCompatButton btnSignup = ViewBindings.findChildViewById(rootView, id);
      if (btnSignup == null) {
        break missingId;
      }

      id = R.id.edT_Birthdate;
      EditText edTBirthdate = ViewBindings.findChildViewById(rootView, id);
      if (edTBirthdate == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.rdB_genderFemale;
      RadioButton rdBGenderFemale = ViewBindings.findChildViewById(rootView, id);
      if (rdBGenderFemale == null) {
        break missingId;
      }

      id = R.id.rdB_genderMale;
      RadioButton rdBGenderMale = ViewBindings.findChildViewById(rootView, id);
      if (rdBGenderMale == null) {
        break missingId;
      }

      id = R.id.rdG_gender;
      RadioGroup rdGGender = ViewBindings.findChildViewById(rootView, id);
      if (rdGGender == null) {
        break missingId;
      }

      id = R.id.tv_datePickerActions;
      TextView tvDatePickerActions = ViewBindings.findChildViewById(rootView, id);
      if (tvDatePickerActions == null) {
        break missingId;
      }

      id = R.id.tv_signup;
      TextView tvSignup = ViewBindings.findChildViewById(rootView, id);
      if (tvSignup == null) {
        break missingId;
      }

      id = R.id.username;
      EditText username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((LinearLayout) rootView, btnBack, btnSignup, edTBirthdate,
          email, password, rdBGenderFemale, rdBGenderMale, rdGGender, tvDatePickerActions, tvSignup,
          username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
