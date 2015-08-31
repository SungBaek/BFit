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
        editor.putFloat(whichNutrients + "TotalFat", n.totalFat);
        editor.putFloat(whichNutrients + "SaturatedFat", n.saturatedFat);
        editor.putFloat(whichNutrients + "TransFat", n.transFat);
        editor.putFloat(whichNutrients + "Cholesterol", n.cholesterol);
        editor.putFloat(whichNutrients + "Sodium", n.sodium);
        editor.putFloat(whichNutrients + "Carbs", n.carbs);
        editor.putFloat(whichNutrients + "Fiber", n.fiber);
        editor.putFloat(whichNutrients + "Protein", n.protein);
        editor.putFloat(whichNutrients + "Sugar", n.sugar);

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
        editor.putInt("mList_size", size);
        for (int i = 0; i < size; i++)
        {
            editor.putInt("mList_"+i+"_totalCount", m.get(i).getTotalCount());
            editor.putString("mList_" + i + "_mealName", m.get(i).getMealName());
            editor.putBoolean("mList_"+i+"_canEat", m.get(i).getCanEat());
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
        user.setExercise(sharedPreferences.getInt("exercise", user.MODERATE));
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
        SharedPreferences sharedPreferences = getSharedPreferences("User_Settings", Context.MODE_PRIVATE);
        MealList ml = new MealList();
        loadNutrients(ml.getTotalNutrients(), "mlTotalNutrients", sharedPreferences);
        loadMeals(ml.getMeals(), sharedPreferences);

    }

    public void loadNutrients(Nutrients n, String whichNutrients, SharedPreferences sp)
    {
        n.calorie = sp.getInt(whichNutrients+"Cal",0);
        n.totalFat = sp.getFloat(whichNutrients + "TotalFat", 0);
        n.saturatedFat = sp.getFloat(whichNutrients + "SaturatedFat", 0);
        n.transFat = sp.getFloat(whichNutrients + "TransFat", 0);
        n.cholesterol = sp.getFloat(whichNutrients + "Cholesterol", 0);
        n.sodium = sp.getFloat(whichNutrients + "Sodium", 0);
        n.carbs = sp.getFloat(whichNutrients + "Carbs", 0);
        n.fiber = sp.getFloat(whichNutrients + "Fiber", 0);
        n.protein = sp.getFloat(whichNutrients + "Protein", 0);
        n.sugar = sp.getFloat(whichNutrients + "Sugar", 0);

        n.vitA = sp.getInt(whichNutrients+"VitA",0);
        n.vitC = sp.getInt(whichNutrients+"VitC",0);
        n.iron = sp.getInt(whichNutrients+"Iron",0);
        n.calcium = sp.getInt(whichNutrients+"Calcium",0);
    }

    public void loadMeals(ArrayList<Meal> m, SharedPreferences sp)
    {
        SharedPreferences.Editor editor = sp.edit();
        int size = sp.getInt("mList_size", 0);

        for (int i = size; i > 0; i--)
        {
            m.get(i).setTotalCount(sp.getInt("mList_" + i + "_totalCount", 0));
            editor.putString("mList_" + i + "_mealName", m.get(i).getMealName());
            editor.putBoolean("mList_"+i+"_canEat", m.get(i).getCanEat());
            editor.apply();

            saveNutrients(m.get(i).getNutrients(), "mList_"+i+"_meal_nut", sp);
        }
        editor.apply();

    }

}
