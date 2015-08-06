package com.example.joon8_000.myapplication.meallist;

import com.example.joon8_000.myapplication.user.UserProfile;
import org.json.JSONObject;

import java.util.HashMap;
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

    //TODO: IMPLEMENT CONSTRUCTOR WITH JSON OBJECT
    public MealList(JSONObject parsed_meals)    {
        size =0;
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
