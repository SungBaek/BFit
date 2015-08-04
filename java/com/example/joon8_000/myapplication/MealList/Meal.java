package com.example.joon8_000.myapplication.MealList;

/**
 * Created by joon8_000 on 7/21/2015.
 */
public class Meal {
    protected String mealName;
    protected int numberEaten;
    protected boolean canEat;
    protected Nutrients nutrients;

    //methods
    public Meal(String mealName, Nutrients nutrients)
    {
        this.mealName = mealName;
        this.nutrients = nutrients;
        numberEaten = 0;
        canEat = true;
    }
    public void eatThis(){ numberEaten++; }
    public String getName() { return mealName; }
    public int getFat() { return nutrients.totalFat; }
    public int getCalorie() {return nutrients.calorie; }
    public int getCount() { return numberEaten; }
    public void setCanEat(boolean canEat) { this.canEat= canEat; }


}
