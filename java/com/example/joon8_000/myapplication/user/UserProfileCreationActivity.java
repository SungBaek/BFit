package com.example.joon8_000.myapplication.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.joon8_000.myapplication.BruinFit;
import com.example.joon8_000.myapplication.R;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

/**
 * Created by joon8_000 on 8/2/2015.
 */
public class UserProfileCreationActivity extends Activity{
    public final static String GENDER = "com.example.joon8_000.myapplication.User.GENDER";
    public final static String WEIGHT = "com.example.joon8_000.myapplication.User.WEIGHT";
    public final static String HEIGHT = "com.example.joon8_000.myapplication.User.HEIGHT";
    public final static String LAST = "com.example.joon8_000.myapplication.User.LAST";
    public final static String FIRST = "com.example.joon8_000.myapplication.User.FIRST";


    public void testParse() {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("movie", "The Matrix"); //why do I need this?
        ParseCloud.callFunctionInBackground("populateDiningHallMenus", params, new FunctionCallback<Float>() {
            @Override
            public void done(Float ratings, ParseException e) {
                if (e == null) {
                    Toast.makeText(null, "it worked", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    public void createUser(View view){
        String firstName = ((EditText)findViewById(R.id.first_name)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.last_name)).getText().toString();
        int gender=0;

        if(((RadioButton)findViewById(R.id.female)).isChecked()){
            gender = UserProfile.FEMALE;
        }
        else if(((RadioButton)findViewById(R.id.male)).isChecked()){
            gender = UserProfile.MALE;
        }
        else{
            //error
        }
        String Sweight =((EditText) findViewById(R.id.weight)).getText().toString();
        int weight = Integer.parseInt(Sweight);
        int height = Integer.parseInt(((EditText) findViewById(R.id.height)).getText().toString());
        int gain = 0;

        if(((RadioButton)findViewById(R.id.lose)).isChecked()){
            gain = UserProfile.LOSE;
        }
        else if(((RadioButton)findViewById(R.id.stay)).isChecked()){
            gain = UserProfile.STAY;
        }
        else if (((RadioButton)findViewById(R.id.gain)).isChecked()){
            gain = UserProfile.GAIN;
        }
        else{
            //error
        }
        int age = 10;
        UserProfile tempUser = new UserProfile(gender,age,weight,height,firstName,lastName,1,gain,true,UserProfile.IMPERIAL);

        saveProfile(tempUser);

        //set this
        ((BruinFit) getApplication()).setUser(tempUser);
        Toast.makeText(getApplicationContext(), "created User!", Toast.LENGTH_SHORT).show();

    }

    public void saveProfile(UserProfile user)
    {

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("gender", user.getGender());
        editor.putInt("age", user.getAge());
        editor.putInt("weight", user.getWeight());
        editor.putInt("height", user.getHeight());
        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.putInt("exercise", user.getExercise());
        editor.putInt("goal", user.getGoal());
        editor.putBoolean("eatBreakfast", user.getEatBreakfast());
        editor.putInt("measurement", user.getMeasurementSyst());

        editor.commit();

    }

    public void loadProfile(View view)
    {
        UserProfile user = new UserProfile();
        SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
        user.setGender(sharedPreferences.getInt("gender", user.MALE));
        user.setAge(sharedPreferences.getInt("age", 0));
        user.setWeight(sharedPreferences.getInt("weight", 0));
        user.setFirstName(sharedPreferences.getString("firstName", ""));
        user.setLastName(sharedPreferences.getString("lastName", ""));
        user.setExercise(sharedPreferences.getInt("exercise", 0));
        user.setGoal(sharedPreferences.getInt("goal", user.STAY));
        user.setEatBreakfast(sharedPreferences.getBoolean("eatBreakfast", true));
        user.setMeasurementSyst(sharedPreferences.getInt("measurement", user.IMPERIAL));

        if (user.getAge() == 0 || user.getWeight() == 0 || user.getFirstName().equals("") || user.getLastName().equals(""))
        {
            Toast.makeText(this, "No user data was found", Toast.LENGTH_LONG).show();
        }
        else
        {
            ((BruinFit) getApplication()).setUser(user);
            Toast.makeText(this, "Data was loaded successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void callEcho(View view) {
        Intent intent = new Intent(this, UserProfileCreationEchoActivity.class);
        EditText weight = (EditText) findViewById(R.id.weight);
        EditText height = (EditText) findViewById(R.id.height);
        EditText last = (EditText) findViewById(R.id.last_name);
        EditText first = (EditText) findViewById(R.id.first_name);
        //convert from edittext to strings
        String msgWeight = weight.getText().toString();
        String msgHeight = height.getText().toString();
        String msgLast = last.getText().toString();
        String msgFirst = first.getText().toString();
        int age = 0;
        //store them 0in intent
        intent.putExtra(WEIGHT, msgWeight);
        intent.putExtra(HEIGHT, msgHeight);
        intent.putExtra(LAST, msgLast);
        intent.putExtra(FIRST, msgFirst);
       // ((BruinFit) this.getApplication()).getUser().setBasicProperties(Integer.getInteger("male"), age, Integer.getInteger(msgWeight),
       //         Integer.getInteger(msgHeight), msgFirst, msgLast);

        testParse();
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
