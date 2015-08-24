package com.example.joon8_000.myapplication.parser;

import org.json.JSONObject;
import java.net.*;
import java.io.*;
/**
 * Created by joon8_000 on 8/3/2015.
 */

//NO USE FOR THIS CODE RIGHT NOW
    //ARCHIEVED
public class Parser {

    private final static String PATH = "http://menu.ha.ucla.edu/foodpro/nutritiveanalysis.asp?";

    private final static String HEDRICK_PATH ="location=06";
    private final static String DENEVE_PATH ="location=01";
    private final static String SPROUL_PATH=""; //CLOSED FOR SUMMER
    private final static String RIEBER_PATH=""; //CLOSED FOR SUMMER
    private final static String COVEL_PATH="locaiton=07";
    private final static String BCAFE_PATH=""; //CLOSER FOR SUMMER
    private final static String CAFE1919_PATH="";
    private final static String RENDE_PATH="";
    private final static String BREAKFAST = "&meal=1";
    private final static String LUNCH = "&meal=2";
    private final static String DINNER = "&meal=3";

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