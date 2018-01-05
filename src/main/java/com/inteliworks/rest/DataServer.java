package com.inteliworks.rest;


import com.inteliworks.Scraper;
import javafx.stage.Screen;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.port;

public class DataServer {



    public static void main(String[] args){

        get("/sbhs/staff", new KikRestData());

     }

}

