package com.example.joon8_000.myapplication.parser;

import org.json.JSONObject;
import java.net.*;
import java.io.*;
/**
 * Created by joon8_000 on 8/3/2015.
 */
public class Parser {

    private final static String HEDRICK_PATH ="http://menu.ha.ucla.edu/foodpro/default.asp?location=06&date=8%2F5%2F2015";
    private final static String DENEVE_PATH ="";
    private final static String SPROUL_PATH="";
    private final static String RIEBER_PATH="";
    private final static String COVEL_PATH="";
    private final static String BCAFE_PATH="";
    private final static String CAFE1919_PATH="";
    private final static String RENDE_PATH="";

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