package com.example.joon8_000.myapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by joon8_000 on 7/21/2015.
 */

/*
TODO: FINISH HASHMAP IMPLEMENTATION
    FINISH CALCULATION WITH HIGHLIGHTS
    I like turtles
 */
public class BruinFitMealList implements MealList{
    protected int size;
    protected HashMap<String, Meal> meals;

    public BruinFitMealList(JSONObject parsed_meals)
    {

    }
    public int calcEateries(UserProfile userProfile)
    {
        int fatCounter  = 0;
        int calorieCounter = 0;
        //for each meal get the total fat and total calorie already eaten.
        //may be it makes more sense to have toal fat and calorie eaten already calculated in UserProfile
      for (Meal chosenMeal : meals)
      {
          fatCounter += chosenMeal.getFat()*chosenMeal.getCount();
          calorieCounter += chosenMeal.getCalorie()*chosenMeal.getCount();
      }
    return 1;
    }
    
    public int eatThis(Meal eatenMeal)
    {
        //do more effecitve implemntation later
        if (meals.containsKey(eatenMeal.getName() ))
        {
            meals.get(eatenMeal.getName() ).eatThis();
        }

        //error
        return -1;
    }

    public List<Meal> getMeals()
    {
        return meals;
    }

}
