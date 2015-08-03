package com.example.joon8_000.myapplication;

/**
 * Created by joon8_000 on 7/23/2015.
 */

//USERTARGET
public class UserProfile {
    //this is a dummy class

    //GENDER
    public static final int MALE = 0;
    public static final int FEMALE = 1;

    //If the user wants to gain, stay, or lose
    public static final int GAIN = 10;
    public static final int STAY = 11;
    public static final int LOSE = 12;

    protected UserTarget breakfast;
    protected UserTarget lunch;
    protected UserTarget dinner;
    protected UserTarget dailyTotal;
    protected UserTarget lifeTotal;
    /*user input variables
    BMI, GENDER, ETC.
    */
    protected int gender;
    protected int weight;
    protected int height;
    protected int goal;
    protected int BMI;



    //calculates initial targets
    public int calculateTarget() {
        if(goal == GAIN){

        }
        else if(goal == LOSE){

        }
        else if(goal == STAY){

        }
        //calculates maxfat from user input
        return -1 ; //so it compiles
    }
    //TODO : return breakfast, lunch, dinner, dailytotal, lifetotal stuff.
    public int getMaxFat() {return breakfast.fat;}
    public int getMaxCalorie() {return dailyTotal.fat; }
    public int getEatenFat() {return eatenFat; }
    public int getEatenCalorie() {return getEatenCalorie;}
    public int getBMI() {return BMI; }

    public UserProfile(int gender, int weight, int height,int goal){
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    }

}
