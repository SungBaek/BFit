package com.example.joon8_000.myapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.joon8_000.myapplication.R;

/**
 * Created by joon8_000 on 8/2/2015.
 */
public class UserProfileCreationActivity extends AppCompatActivity{
    public final static String GENDER = "com.example.joon8_000.myapplication.User.GENDER";
    public final static String WEIGHT = "com.example.joon8_000.myapplication.User.WEIGHT";
    public final static String HEIGHT = "com.example.joon8_000.myapplication.User.HEIGHT";


    public void createUserProfile(View view) {
        Intent intent = new Intent(this, UserProfileCreationEchoActivity.class);
        EditText gender = (EditText) findViewById(R.id.gender);
        EditText weight = (EditText) findViewById(R.id.weight);
        EditText height = (EditText) findViewById(R.id.height);
        //convert from edittext to strings
        String msgGender = gender.getText().toString();
        String msgWeight = weight.getText().toString();
        String msgHeight = height.getText().toString();
        //store them 0in intent
        intent.putExtra(GENDER, msgGender);
        intent.putExtra(WEIGHT, msgWeight);
        intent.putExtra(HEIGHT, msgHeight);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_profile_user);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
