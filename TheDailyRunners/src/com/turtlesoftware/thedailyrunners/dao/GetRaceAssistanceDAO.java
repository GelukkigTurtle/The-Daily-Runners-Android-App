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

public class GetRaceAssistanceDAO extends AsyncTask<String, String, Integer> {
	
	JSONArray response = null;
	
    public GetRaceAssistanceDAO (){
    	
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		

	}
	@Override
	protected Integer  doInBackground(String... args) {
		int assistance = 0;

		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("fb_id",args[0]));
		nameValuePairs.add(new BasicNameValuePair("race_id",args[1]));

		// Obteins JSON from URL
		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_GET_ASSISTANCE,nameValuePairs);
		try {
			// Obteins JSON Array

			response = json.getJSONArray(JSONTags.TAG_ASSISTANCE_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < response.length(); i++) {
				JSONObject c = response.getJSONObject(i);
				assistance= c.getInt(JSONTags.TAG_RESULT);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return assistance;
	}

	@Override
	protected void onPostExecute(Integer json) {
		
		
	}

}
