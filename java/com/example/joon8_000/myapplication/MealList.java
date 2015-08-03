package com.example.joon8_000.myapplication;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by joon8_000 on 7/21/2015.
 */
public interface MealList {


    public int calcEateries(UserProfile userProfile);
    public int eatThis(Meal eatenMeal);
    public List<Meal> getMeals();
}
