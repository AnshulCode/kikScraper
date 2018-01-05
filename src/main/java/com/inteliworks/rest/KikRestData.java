package com.inteliworks.rest;

<<<<<<< HEAD

public class KikRestData {
=======
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

>>>>>>> 770b394d96173e591bda14417bfa4a108ce91282


}

