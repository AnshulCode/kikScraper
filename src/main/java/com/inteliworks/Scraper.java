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
		System.out.println(ja.toString());
		//end

	}// end of void main

	private static JSONObject parse(String line) throws JSONException {

		StringTokenizer st = new StringTokenizer(line, " ");
		String firstName = "";
		String lastName = "";

		String assigment = "";
		if (st.hasMoreTokens())
			firstName = st.nextToken();
		if (st.hasMoreTokens())
			lastName = st.nextToken();
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
		json.put("firstname", lastName);
		json.put("lastname", firstName);
		json.put("assigment", assigment);
		json.put("phonenumber", phoneNumber);

		return json;
	}
}
