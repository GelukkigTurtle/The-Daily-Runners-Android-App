package com.turtlesoftware.thedailyrunners.dao;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class UserDAO extends AsyncTask<String, String, Void> {
	
    public UserDAO (){
         
    }
	
	@Override
	protected void onPreExecute() {
		
		
	}
	@Override
	protected Void doInBackground(String... args) {
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("fb_id",args[0]));
		nameValuePairs.add(new BasicNameValuePair("u_name",args[1]));
		nameValuePairs.add(new BasicNameValuePair("u_lastname",args[2]));
		nameValuePairs.add(new BasicNameValuePair("u_email",args[3]));

		// Obteins JSON from URL Execute

		@SuppressWarnings("unused")
		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_INSERT_USER,nameValuePairs);
		
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {	
		
	}


}
