package com.socialmediaapp.View.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.socialmediaapp.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edT_Birthdate;
    TextView tv_datePickerActions;
    private int Year, Month, Day;
    AppCompatButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edT_Birthdate = findViewById(R.id.edT_Birthdate);
        tv_datePickerActions = findViewById(R.id.tv_datePickerActions);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        tv_datePickerActions.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == tv_datePickerActions){
            final Calendar calendar = Calendar.getInstance();
            Year = calendar.get(Calendar.YEAR);
            Month = calendar.get(Calendar.MONTH);
            Day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (date, year, monthOfYear, dayOfMonth) -> edT_Birthdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), Year, Month, Day);
            datePickerDialog.show();
        }
        else if(view == btn_back){
            Intent backToLogin = new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}