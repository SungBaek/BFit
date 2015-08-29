package com.example.joon8_000.myapplication.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.joon8_000.myapplication.BruinFit;
import com.example.joon8_000.myapplication.R;

public class ViewUserProfile extends Activity {

    TextView tv_name, tv_age, tv_gender, tv_weight, tv_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_user_profile);

        UserProfile tempUser = ((BruinFit) getApplication()).getUser();

        String userName = tempUser.getFirstName() + " " + tempUser.getLastName();
        String age = String.valueOf(tempUser.getAge());
        String gender = "";
        if (tempUser.getGender() == UserProfile.MALE)
            gender = "Male";
        else
            gender = "Female";

        String weight = String.valueOf(tempUser.getWeight());
        String height = String.valueOf(tempUser.getHeight());

        tv_name = (TextView) findViewById(R.id.user_name);
        tv_name.setText(userName);

        tv_age = (TextView) findViewById(R.id.user_age);
        tv_age.setText(age);

        tv_gender = (TextView) findViewById(R.id.user_gender);
        tv_gender.setText(gender);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
