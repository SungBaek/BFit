package com.example.joon8_000.myapplication.meallist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joon8_000.myapplication.R;

import java.util.ArrayList;

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
        TextView calorie = (TextView) convertView.findViewById(R.id.hmm);
        mealName.setText(meal.getName());
        calorie.setText(String.valueOf(meal.getCalorie()));
        convertView.setBackgroundColor(Color.YELLOW);
        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        Meal m = getItem(position);
        return m.getCanEat();

    }
}


