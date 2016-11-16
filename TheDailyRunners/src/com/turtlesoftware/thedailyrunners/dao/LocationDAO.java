package com.turtlesoftware.thedailyrunners.dao;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class LocationDAO extends AsyncTask<String, String, Void> {
	
	JSONArray jarray = null;	
    public LocationDAO (){
    	
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		

	}
	@Override
	protected Void  doInBackground(String... args) {
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("lat",args[0]));
		nameValuePairs.add(new BasicNameValuePair("long",args[1]));
		// Obteins JSON from URL
		
		

		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_COUNTRY,nameValuePairs);
		try {
			// Obteins JSON Array

			jarray = json.getJSONArray(JSONTags.TAG_LOCATION_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < jarray.length(); i++) {
				
				@SuppressWarnings("unused")
				JSONObject c = jarray.getJSONObject(i);

			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {
		
	}

}
