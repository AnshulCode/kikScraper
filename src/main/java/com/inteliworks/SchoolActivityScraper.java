package com.inteliworks;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SchoolActivityScraper {
    static JSONArray jsonArray = new JSONArray();

    public static void main(String[] args) throws JSONException {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.sbschools.org/schools/sbhs/activities/index.php").get();
            if (doc == null) {
                System.out.println("Error getting data.");
                return;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Element table = doc.getElementById("content");
        Elements rows = table.getElementsByTag("tBody");
        String wantedRow = rows.get(1).text();

        parse(wantedRow);


    }

    private static String parse(String line) {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("event", "Wear Teal, Get a Deal");
        jsonObject.put("when", " September 20");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "SBHS Goes PINK");
        jsonObject.put("when", " October 12");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Homecoming Spirit Week");
        jsonObject.put("when", " October 9 - 13");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Homecoming");
        jsonObject.put("when", "October 13");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "SBHS Supports Our Troops");
        jsonObject.put("when", "Month of November");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Powder Puff Football");
        jsonObject.put("when", "November 15-16(contingent on football playoffs)");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Deck The Halls");
        jsonObject.put("when", "December 9");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Month of Giving");
        jsonObject.put("when", "Entire Month of December");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Winter Sprit Week");
        jsonObject.put("when", "January 8-12");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Winter Pep Rally");
        jsonObject.put("when", "January 12");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Hugs-4-Brady Blacklight Dance");
        jsonObject.put("when", "January 26");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Valentine's Gram Sales");
        jsonObject.put("when", "February 7-9");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "SBHS Has Heart");
        jsonObject.put("when", "February 14");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "SBHS Feeds The Hungry");
        jsonObject.put("when", "Month of March");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Mr SBHS ");
        jsonObject.put("when", "March 16");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Spring Spirit Week");
        jsonObject.put("when", "April 2-6");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "Spring Pep Rally");
        jsonObject.put("when", "April 6");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "SBHS Goes Blue");
        jsonObject.put("when", "April 26");
        jsonArray.put(jsonObject);

        jsonObject = new JSONObject();

        jsonObject.put("event", "We Bleed Black And Gold");
        jsonObject.put("when", "May 30 and May 31");
        jsonArray.put(jsonObject);

        return jsonArray.toString();
    }

    public static JSONObject getEventInfo(String info) {
        int aLength = jsonArray.length();
        info = info;
        JSONObject o = null;
        for (int i = 0; i < aLength; i++) {
            o = (JSONObject) jsonArray.get(i);
            //System.out.println(o.toString());
            String event = o.getString("event");
            if (event != null && event.equalsIgnoreCase(info))
                return o;
            String when = o.getString("when");
            if (when != null && when.equalsIgnoreCase(info))
                return o;

        }
        return null;
    }
}
