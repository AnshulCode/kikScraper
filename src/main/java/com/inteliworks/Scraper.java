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

public class Scraper {
	static JSONArray ja = new JSONArray();

	public static void main(String[] args) throws JSONException {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.sbschools.org/schools/sbhs/staff.php").get();
			if(doc == null) {
				System.out.println("Error getting data.");
				return;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		Element table = doc.getElementById("content");
		Elements rows = table.getElementsByTag("TR");
		Elements tableC = table.getElementsByTag("TD");
		for (Element row : rows) {
			String currentRow = row.text();
			JSONObject obj = parse(currentRow);
			
			if(!obj.getString("firstname").equals("") && !obj.getString("firstname").startsWith("A B C D"))
				ja.put(obj);
		}
		//System.out.println(ja.toString());
		//end
            System.out.println(getContact("sadowsky"));
	}// end of void main

	private static JSONObject parse(String line) throws JSONException {

		StringTokenizer st = new StringTokenizer(line, " ");
		String firstName = "";
		String lastName = "";

		String assigment = "";
		if (st.hasMoreTokens())
			lastName = st.nextToken();
		if (st.hasMoreTokens())
			firstName = st.nextToken();
		String phoneNumber = "";
		String tmp;
		while (st.hasMoreTokens()) {
			tmp = st.nextToken();
			if (tmp.startsWith("732")) {
				phoneNumber = tmp;
				phoneNumber = tmp + st.nextToken();
				break;
			}
			assigment = assigment + tmp;
		}
		JSONObject json = new JSONObject();
		json.put("firstname", firstName);
		json.put("lastname", lastName);
		json.put("assigment", assigment);
		json.put("phonenumber", phoneNumber);

		return json;
	}
	public static JSONObject getContact(String name){
		int aLength= ja.length();
		JSONObject o = null;
        name = name +",";
		for(int i = 0; i < aLength; i++){
			o =  (JSONObject)ja.get(i);
			//System.out.println(o.toString());
			String lastname = o.getString("lastname");
			if(lastname != null && lastname.equalsIgnoreCase(name))
				return o;
			String firstname = o.getString("firstname");
			if(firstname != null && firstname.equalsIgnoreCase(name))
				return o;

		}
		return null;
	}
}
