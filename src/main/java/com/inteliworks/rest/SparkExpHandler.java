package com.inteliworks.rest;

import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class SparkExpHandler implements ExceptionHandler{
    public void handle(Exception e, Request request, Response response) {
        System.out.println("Error : " + e);
        e.printStackTrace();
    }
}
