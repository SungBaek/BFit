package com.example.joon8_000.myapplication;

import java.lang.String;
/**
 * Created by joon8_000 on 7/21/2015.
 */
public class Meal {
    protected String mealName;
    protected int fat;
    protected int calorie;
    protected int numberEaten;
    protected boolean canEat;

    //methods
    public Meal(String i_name, int i_fat, int i_calorie)
    {
        mealName = i_name;
        fat = i_fat;
        calorie = i_calorie;
        numberEaten = 0;
        canEat = true;
    }
    public void eatThis(){ numberEaten++; }
    public String getName() { return mealName; }
    public int getFat() { return fat; }
    public int getCalorie() {return calorie; }
    public int getCount() { return numberEaten; }



}
