package com.inteliworks.rest;

import com.inteliworks.Scraper;
import org.json.JSONArray;
import spark.Request;
import spark.Route;



import spark.Spark;


import spark.Response;
import org.json.JSONObject;

public class KikRestData implements Route{
    public Object handle(Request request, Response response) {

        Scraper s = new Scraper();

        return s.getJSONArrayStaff().toString();
    }



}
