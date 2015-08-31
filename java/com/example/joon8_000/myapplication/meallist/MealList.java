package com.example.joon8_000.myapplication.meallist;

import android.util.Log;

import com.example.joon8_000.myapplication.user.UserProfile;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by joon8_000 on 7/21/2015.
 */

/*
        Just tracking calories and fat for now. will add stuff later
        Need to decide if we are able to take the "food triangle" into consideration.
        eventually add a variable for
 */
public class MealList {

    protected ArrayList<Meal> meals;

    public Nutrients totalNutrients;

    public String[] printTen(){
        String[] poo = new String[10];
        for (int i = 0 ; i < 10 && i < meals.size(); i++){
            poo[i] = meals.get(i).getName();
        }
        return poo;
    }

    //get all objects and save it into the hash map.
    public MealList()    {

       //TODO:eventaully add a variable here for dining hall
       ParseQuery<ParseObject> query = ParseQuery.getQuery("Nutrients");
       this.totalNutrients = new Nutrients();
       this.meals = new ArrayList<>();

       try {
           List<ParseObject> list  = query.find();
           //for each list go through it and put it in the hash map.
           for (ParseObject e : list){
               meals.add(objectParser(e));
           }
       }
       catch (ParseException e) { //failed to retrieve with given query
            Log.e("meallist", "Failed to find any objects");
       }

    }

    //helper function for calculating total
    protected void calcTotal(int foodTime){
        //for each meal get the total fat and total calorie already eaten.
        //iterate through every entry and add them all
        for(Meal e : meals) {
            totalNutrients.addNutrients(e.getNutrients());
        }
    }
    //helper function for parsing parseobject
    public Meal objectParser(ParseObject e){
        Meal m;
        Nutrients nutrient = new Nutrients();
        //calorie
        nutrient.calorie = e.getInt("calorie"); // data are stored as an array of size 1
        //totalfat
        nutrient.totalFat = e.getInt("totFat");
        //saturatedfat
        nutrient.saturatedFat = e.getInt("satFat");
        //transfat
        nutrient.transFat = e.getInt("transFat");
        //cholesterol
        nutrient.cholesterol = e.getInt("chol");
        //sodium
        nutrient.sodium = e.getInt("sod");
        //fiber
        nutrient.fiber = e.getInt("fiber");
        //protein.
        nutrient.protein = e.getInt("protein");
        //sugar
        nutrient.sugar = e.getInt("sugar");
        //name
        String name = e.getString("name");

        m = new Meal(name, nutrient);
        return m;
    }

    //separate for breakfast, lunch, dinner?
    public int calcEateries(UserProfile userProfile, int foodTime)
    {
        int a = meals.get(0).getNutrients().calorie;
        int b = this.totalNutrients.calorie;

        for(Meal chosenMeal : meals) {
            //go through every elements in meals then see if nutrientvalue + current >= maxallowed.
            if (chosenMeal.getNutrients().calorie > userProfile.getTarget(foodTime).calorie - this.totalNutrients.calorie
                    || chosenMeal.getNutrients().totalFat > userProfile.getTarget(foodTime).totalFat - this.totalNutrients.totalFat) {
                chosenMeal.setCanEat(false);
            }
            else
                chosenMeal.setCanEat(true);

        }
        return 0; //so it compiles
    }

    //AUX FUNCTIONS
    public Meal getMeal(String s) {
        for (Meal e : this.meals){
            if (e.getName() == s){
                return e;
            }
        }
        //couldn't find it, return a dummy meal?
        Nutrients tempN = new Nutrients();
        Meal temp = new Meal("dummy", tempN);
        return temp;
    }
    public ArrayList<Meal> getMeals()
    { return meals; }
    public void addCalorie(int calorie){
        this.totalNutrients.calorie += calorie;
    }
    public void addFat(int fat){
        this.totalNutrients.totalFat += fat;
    }
    public Nutrients getTotalNutrients(){
        return this.totalNutrients;
    }

}
