package com.turtlesoftware.thedailyrunners.dao;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class SetRaceAssistanceDAO extends AsyncTask<String, String, Void> {
	
    public SetRaceAssistanceDAO (){
         
    }
	
	@Override
	protected void onPreExecute() {
		
		
	}
	@Override
	protected Void doInBackground(String... args) {
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("fb_id",args[0]));
		nameValuePairs.add(new BasicNameValuePair("race_id",args[1]));
		nameValuePairs.add(new BasicNameValuePair("type",args[2]));

		// Obteins JSON from URL Execute

		@SuppressWarnings("unused")
		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_SET_ASSISTANCE,nameValuePairs);
		
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {	
		
	}
}
