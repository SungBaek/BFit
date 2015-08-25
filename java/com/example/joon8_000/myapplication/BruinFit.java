package com.example.joon8_000.myapplication;

import android.app.Application;

import com.example.joon8_000.myapplication.meallist.MealList;
import com.example.joon8_000.myapplication.user.UserProfile;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by joon8_000 on 8/5/2015.
 */
public class BruinFit extends Application {
    private MealList mealList;
    private UserProfile user;

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

        //intialize local data here
        UserProfile up;
        MealList ml;

    }
}
