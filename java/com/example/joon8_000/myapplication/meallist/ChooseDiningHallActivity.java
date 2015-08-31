package com.example.joon8_000.myapplication.meallist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.joon8_000.myapplication.R;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

public class ChooseDiningHallActivity extends ActionBarActivity {

    public void destroyData(View view){
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("movie", "The Matrix"); //why do I need this?
        ParseCloud.callFunctionInBackground("flushNutrients", params, new FunctionCallback<Float>() {
            @Override
            public void done(Float ratings, ParseException e) {
                if (e == null) {
                    Toast.makeText(null, "it worked", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    public void populateData(View view){
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


    public void openHedrick(View view){
        Intent intent = new Intent(this, PickFoodTypeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_dining_hall);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_dining_hall, menu);
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
