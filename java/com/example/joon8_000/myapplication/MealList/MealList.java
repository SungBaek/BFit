package com.example.joon8_000.myapplication.meallist;

import android.util.Log;

import com.example.joon8_000.myapplication.user.UserProfile;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by joon8_000 on 7/21/2015.
 */

/*
        Just tracking calories and fat for now. will add stuff later
        Need to decide if we are able to take the "food triangle" into consideration.
 */
public class MealList {

    protected int size;
    protected HashMap<String, Meal> meals;
    protected Nutrients userTarget;
//TODO: MAYBE HAVE TOTAL FAT AND CALORIE COUNTERS PRECALCULATED

    //get all objects and save it into the hash map.
    public MealList(Nutrients userTarget)    {
        this.userTarget = userTarget;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Nutrients");

       try {
           List<ParseObject> list  = query.find();
           //for each list go through it and put it in the hash map.
           for (ParseObject e : list){
               Meal m = objectParser(e);
               meals.put(m.getName(), m);
           }

       }
       catch (ParseException e) { //failed to retrieve with given query
            Log.e("meallist", "Failed to find any objects");
       }

    }

    //helper function for parsing parseobject
    protected Meal objectParser(ParseObject e){
        Meal m;
        Nutrients nutrient = new Nutrients();
        //calorie
        int[] temp = new int[1];
        temp = (int[]) e.get("calorie"); // data are stored as an array of size 1
        nutrient.calorie = temp[0];
        //totalfat
        temp = (int[]) e.get("totFat");
        nutrient.totalFat = temp[0];
        //saturatedfat
        temp = (int[]) e.get("satFat");
        nutrient.saturatedFat = temp[0];
        //transfat
        temp = (int[]) e.get("transFat");
        nutrient.transFat = temp[0];
        //cholesterol
        temp = (int[]) e.get("chol");
        nutrient.cholesterol = temp[0];
        //sodium
        temp = (int[]) e.get("sod");
        nutrient.sodium = temp[0];
        //fiber
        temp = (int[]) e.get("fiber");
        nutrient.fiber = temp[0];
        //protein.
        temp = (int[]) e.get("protein");
        nutrient.protein = temp[0];
        //sugar
        temp = (int[]) e.get("sugar");
        nutrient.sugar = temp[0];
        //name
        String[] tempName = (String[]) e.get("name");
        String name = tempName[0];

        m = new Meal(name, nutrient);
        return m;
    }

    //separate for breakfast, lunch, dinner?
    public int calcEateries(UserProfile userProfile, int foodTime)
    {
        int fatCounter  = 0;
        int calorieCounter = 0;
        //for each meal get the total fat and total calorie already eaten.
        //iterate through every entry and add them all
        //TODO: find a more efficient implementation
        for(Map.Entry<String, Meal> chosenMeal : meals.entrySet()) {
             String key = chosenMeal.getKey();
             Meal value = chosenMeal.getValue();

             fatCounter += value.getFat()*value.getCount(foodTime);
             calorieCounter += value.getCalorie()*value.getCount(foodTime);
        }
        for(Map.Entry<String, Meal> chosenMeal : meals.entrySet()) {
            //go through every elements in meals then see if nutrientvalue + current >= maxallowed.
            if (chosenMeal.getValue().getNutrients().calorie > userProfile.getTarget(foodTime).calorie - calorieCounter
                    || chosenMeal.getValue().getNutrients().totalFat > userProfile.getTarget(foodTime).totalFat - fatCounter) {
                chosenMeal.getValue().setCanEat(false);
            }
            else
                chosenMeal.getValue().setCanEat(true);

        }
        return 0; //so it compiles
    }

    //returns null if it doesn't work
    //TODO: return a dummy meal if it doesn't work??
    public Meal getMeal(String s) { return meals.get(s); }
    public HashMap<String, Meal> getMeals()
    { return meals; }
}
