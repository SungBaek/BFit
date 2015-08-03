package com.example.joon8_000.myapplication.Parser;

import android.widget.Switch;

import org.json.JSONObject;

/**
 * Created by joon8_000 on 8/3/2015.
 */
public class Parser {

    public JSONObject getJSON(String hallName) {
        //TODO: make a parser for each dining hall
        switch (hallName) {
            case "hedrick":
                //do parsing here
                break;
            case "deNeve":
                //do parsing here
                break;
            case "covel":
                break;
            case "feast":
                break;
            case "rende":
                break;
            case "bCafe":
                break;
            case "1919":
                break;
            default: //error
                break;

        }
        //NOTE: there are here so it compiles
        JSONObject hi = new JSONObject();
        return hi;
    }
}