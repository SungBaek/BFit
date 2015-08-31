package com.example.joon8_000.myapplication.meallist;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.joon8_000.myapplication.BruinFit;
import com.example.joon8_000.myapplication.R;
import com.example.joon8_000.myapplication.user.UserProfile;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PickFoodTypeActivity extends AppCompatActivity {
    //TODO: create custom adapters for meal Class.
    //TODO: set onitemlistener for interactions.

    //test function
    public void testParseQuery(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Nutrients");

        List<Integer> temp = new Vector<>();
        try {
            List<ParseObject> list  = query.find();
            //for each list go through it and put it in the hash map.
            for (ParseObject e : list){
                 Log.i("mealllist",e.getString("name"));
            }

        }
        catch (ParseException e) { //failed to retrieve with given query
            Log.e("meallist", "Failed to find any objects");
        }
    }

    public void testMealList(){
        String[] temp = ((BruinFit) getApplication()).getMeal().printTen();
        for ( String e : temp){
            Log.i("meallist", e);
        }
    }
    public void createMeal(){
        ((BruinFit) getApplication()).setMealList(1); //1 is a dummy
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_food_type);
        createMeal();
        testMealList();
        ArrayList<Meal> meals = ((BruinFit) getApplication()).getMeal().getMeals();

        MealAdapter adapter = new MealAdapter(this, meals);

        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Meal m = (Meal) parent.getItemAtPosition(position);
                MealList ml = ((BruinFit) getApplication()).getMeal();
                UserProfile u = ((BruinFit) getApplication()).getUser();

                Toast.makeText(getBaseContext(), m.getName(), Toast.LENGTH_LONG).show();
                m.eatThis(ml, UserProfile.DAILY);
                ml.calcEateries(u, UserProfile.DAILY);
                view.setBackgroundColor(Color.BLUE);
            }
        });
      //  ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>
      // (this, android.R.layout.simple_list_item_1, codeLearnChapters);
      //  ListView codeLearnLessons = (ListView)findViewById(R.id.listview);
      //  codeLearnLessons.setAdapter(codeLearnArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_food_type, menu);
        return true;   }

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
