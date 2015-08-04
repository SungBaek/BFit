package com.example.joon8_000.myapplication.User;

import com.example.joon8_000.myapplication.MealList.MealList;
import com.example.joon8_000.myapplication.MealList.Nutrients;

/**
 * Created by joon8_000 on 7/23/2015.
 */

//USERTARGET
public class UserProfile {
    //GENDER
    public static final int MALE = 0;
    public static final int FEMALE = 1;
    //If the user wants to gain, stay, or lose
    public static final int GAIN = 10;
    public static final int STAY = 11;
    public static final int LOSE = 12;

    public static final int BREAKFAST = 20;
    public static final int LUNCH = 21;
    public static final int DINNER = 22;
    public static final int TOTAL = 23;
    public static final int DAILY = 24;

    protected Nutrients brkTarget;
    protected Nutrients lunchTarget;
    protected Nutrients dinnerTarget;
    protected Nutrients dailyTarget;

    protected Nutrients lifeTotal;
    /*user input variables
    BMI, GENDER, ETC.
    */
    private String firstName;
    private String lastName;

    protected int gender;
    protected int weight;
    protected int height;
    protected int BMI;
    protected int goal;

    private MealList mealList;

    //constructor
    public UserProfile(int gender, int weight, int height,String firstName, String lastName){
        if (gender != MALE && gender != FEMALE)
            ; //error so probably throw exception here.
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //BMIcalculator, calculates BMI using weight and height.
    public int calcBMI(int weight, int height){
        this.BMI = 10;
        //TODO: IMPLEMENT THIS
        return 0; //so it compiles.
    }

    //calculates initial targets
    //depending on if the user wants to gain lose or stay set 4 targets
    //brkTarget, lunchTarget, dinnerTarget, dailyTarget
    public int calculateTarget() {
        if(goal == GAIN){
            //do calculation here
        }
        else if(goal == LOSE){
            //do calculation here
        }
        else if(goal == STAY){
            //do calculatiion here
        }

        return -1 ; //so it compiles
    }
    //TODO : return breakfast, lunch, dinner, dailytotal, lifetotal stuff.
    public Nutrients getTarget(int target){
        if (target == BREAKFAST){
            return brkTarget;
        }
        else if (target == LUNCH){
            return lunchTarget;
        }
        else if (target == DINNER){
            return dinnerTarget;
        }
        else{
            return null; //it's error
        }

    }


    //some AUX functions
    public int setGender(int gender){
        if (gender != MALE && gender != FEMALE){
            return -1; // error
        }
        this.gender = gender;
        return 0; //success
    }

    public void setWeight(int weight) { this.weight = weight; }
    public void setHeight(int height) {this.height = height; }
    public int getWeight() { return weight;}
    //TODO: finish creating auxiliary functions for weight, height, target.
}
