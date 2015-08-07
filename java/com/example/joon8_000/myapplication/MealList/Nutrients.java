package com.example.joon8_000.myapplication.meallist;

/**
 * Created by joon8_000 on 8/3/2015.
 */
public class Nutrients {
    public int calorie;
    public int totalFat;
    public int saturatedFat;
    public int transFat;
    public int cholesterol;     // mg
    public int sodium;          // mg
    public int carbs;
    public int fiber;
    public int protein;
    public int sugar;
    // vitamins are not dependent on caloric intake, shoot for 100% on Nutrition Facts
    public int vitA;
    public int vitC;
    public int iron;
    public int calcium;

    public Nutrients()
    {
        calorie = 0;
        totalFat = 0;
        saturatedFat = 0;
        transFat = 0;
        cholesterol = 0;     // mg
        sodium = 0;          // mg
        carbs = 0;
        fiber = 0;
        protein = 0;
        sugar = 0;
        // vitamins are not dependent on caloric intake, shoot for 100% on Nutrition Facts
        vitA = 0;
        vitC = 0;
        iron = 0;
        calcium = 0;
    }
}
