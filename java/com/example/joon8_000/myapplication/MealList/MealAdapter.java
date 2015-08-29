package com.example.joon8_000.myapplication.meallist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joon8_000.myapplication.R;
import com.example.joon8_000.myapplication.user.UserProfile;

import java.util.ArrayList;

/**
 * Created by joon8_000 on 8/28/2015.
 */
public class MealAdapter extends ArrayAdapter<Meal> {
    public MealAdapter(Context context, ArrayList<Meal> meals){
        super(context,0, meals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Meal meal = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_meal, parent, false);
        }
        TextView mealName = (TextView) convertView.findViewById(R.id.mealName);
        mealName.setText(meal.getName());

        return convertView;

    }
}


