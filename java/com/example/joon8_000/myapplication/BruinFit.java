package com.example.joon8_000.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.joon8_000.myapplication.meallist.Meal;
import com.example.joon8_000.myapplication.meallist.MealList;
import com.example.joon8_000.myapplication.meallist.Nutrients;
import com.example.joon8_000.myapplication.user.UserProfile;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by joon8_000 on 8/5/2015.
 */
public class BruinFit extends Application {
    private MealList mealList;
    private UserProfile user;

    public void startMealList(MealList m){
    //check if there is data, if not just end.

    }

    public void startUser(UserProfile u){
        if (u.getAge() == 0 || u.getWeight() == 0 || u.getFirstName().equals("") || u.getLastName().equals(""))
            this.user = new UserProfile();

    }
    public MealList getMeal(){
        return this.mealList;
    }
    public UserProfile getUser(){
        return user;
    }
    public void setUser(UserProfile user){
        this.user = user;
    }
    public void setMealList(int time) {
        //int might eventually be used depends
        this.mealList = new MealList();

    }

    @Override
    public void onCreate() {
        super.onCreate();

        //parse stuff
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "KQSKXYSay5RZQF0lDhzJjB8srFghEDY7jxSu10r9", "AVGALph0YGKRyJDXbUvdsKK6tTVOlg4tcbapwnyH");

        //test Parse Connection
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        loadProfile();

        startUser(user);
        startMealList(mealList);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        saveProfile(user);
        saveMealList(mealList);

    }

    public void saveProfile(UserProfile user)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("User_Settings", Context.MODE_PRIVATE);
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

        editor.apply();

    }

    public void saveMealList(MealList ml)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("User_Settings", Context.MODE_PRIVATE);
        saveNutrients(ml.getTotalNutrients(), "mlTotalNutrients", sharedPreferences);
        saveMeals(ml.getMeals(), sharedPreferences);

    }

    public void saveNutrients(Nutrients n, String whichNutrients, SharedPreferences sp)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(whichNutrients+"Cal", n.calorie);
        editor.putInt(whichNutrients+"TotalFat", n.totalFat);
        editor.putInt(whichNutrients+"SaturatedFat", n.saturatedFat);
        editor.putInt(whichNutrients+"TransFat", n.transFat);
        editor.putInt(whichNutrients+"Cholesterol", n.cholesterol);
        editor.putInt(whichNutrients+"Sodium", n.sodium);
        editor.putInt(whichNutrients+"Carbs", n.carbs);
        editor.putInt(whichNutrients+"Fiber", n.fiber);
        editor.putInt(whichNutrients+"Protein", n.protein);
        editor.putInt(whichNutrients+"Sugar", n.sugar);

        editor.putInt(whichNutrients+"VitA", n.vitA);
        editor.putInt(whichNutrients+"VitC", n.vitC);
        editor.putInt(whichNutrients+"Iron", n.iron);
        editor.putInt(whichNutrients+"Calcium", n.calcium);

        editor.apply();
    }

    public void saveMeals(ArrayList<Meal> m, SharedPreferences sp)
    {
        int size = m.size();
        SharedPreferences.Editor editor = sp.edit();
        for (int i = 0; i < size; i++)
        {
            editor.putInt("mList_"+i+"_totalCount", m.get(i).getTotalCount());
            editor.putString("mList_" + i + "_mealName", m.get(i).getMealName());
            editor.putBoolean("mList_"+i+"_canEat", m.get(i).getCatEat());
            editor.apply();

            saveNutrients(m.get(i).getNutrients(), "mList_"+i+"_meal_nut", sp);
        }
        editor.apply();

    }


    public void loadProfile()
    {
        UserProfile user = new UserProfile();
        SharedPreferences sharedPreferences = getSharedPreferences("User_Settings", Context.MODE_PRIVATE);
        user.setGender(sharedPreferences.getInt("gender", user.MALE));
        user.setAge(sharedPreferences.getInt("age", 0));
        user.setWeight(sharedPreferences.getInt("weight", 0));
        user.setFirstName(sharedPreferences.getString("firstName", ""));
        user.setLastName(sharedPreferences.getString("lastName", ""));
        user.setExercise(sharedPreferences.getInt("exercise", 0));
        user.setGoal(sharedPreferences.getInt("goal", user.STAY));
        user.setEatBreakfast(sharedPreferences.getBoolean("eatBreakfast", true));
        user.setMeasurementSyst(sharedPreferences.getInt("measurement", user.IMPERIAL));
        user.calcBMR();
        user.calculateTarget();

        /*
        if (user.getAge() == 0 || user.getWeight() == 0 || user.getFirstName().equals("") || user.getLastName().equals(""))
        {
            Toast.makeText(this, "No user data was found", Toast.LENGTH_LONG).show();
        }
        else
        {
            setUser(user);
            Toast.makeText(this, "Data was loaded successfully!", Toast.LENGTH_SHORT).show();
        }
        */

        setUser(user);

    }

    public void loadMealList()
    {

    }

    public void loadNutrients(Nutrients n, String whichNutrients, SharedPreferences sp)
    {

    }
}
