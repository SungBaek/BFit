package com.example.joon8_000.myapplication;

import android.app.Application;

import com.example.joon8_000.myapplication.meallist.MealList;
import com.example.joon8_000.myapplication.meallist.Nutrients;
import com.example.joon8_000.myapplication.user.UserProfile;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by joon8_000 on 8/5/2015.
 */
public class BruinFit extends Application {
    private MealList mealList;
    private UserProfile user;

    public void startMealList(MealList m){
    //check if there is data, if not just end.

    }

    public void startUser(UserProfile u){
;   //check if there is data, if not wait for createuserprofile.
        this.user = new UserProfile();
    }
    public MealList getMeal(){
        return this.mealList;
    }
    public UserProfile getUser(){
        return user;
    }
    public void setUser(UserProfile user){
        this.user = user;
    }
    public void setMealList(int time) {
        //int might eventually be used depends
        this.mealList = new MealList();

    }

    @Override
    public void onCreate() {
        super.onCreate();

        //parse stuff
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "KQSKXYSay5RZQF0lDhzJjB8srFghEDY7jxSu10r9", "AVGALph0YGKRyJDXbUvdsKK6tTVOlg4tcbapwnyH");

        //test Parse Connection
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        startUser(user);
        startMealList(mealList);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        //do saving data stuff here! :Ds
    }
}
