package com.turtlesoftware.thedailyrunners.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.fragments.MenuMyRacesFragment;
import com.turtlesoftware.thedailyrunners.interfaces.Item;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class GetMyCalendarDAO extends AsyncTask<String, String,Void> {
	
	JSONArray races = null;
	ProgressBar progressbar;
	
    public GetMyCalendarDAO (Context context){

    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		MenuMyRacesFragment.pBar.setIndeterminate(true);
		MenuMyRacesFragment.pBar.setVisibility(ProgressBar.VISIBLE);

	}
	@Override
	protected Void doInBackground(String... args) {
		Log.d(Configuration.LOG_TAG, "doInBackground() - Calendar");

		Race r =  null;
		MenuMyRacesFragment.races = new ArrayList<Item>();
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();	
		nameValuePairs.add(new BasicNameValuePair("fb_id",args[0]));
		// Obteins JSON from URL
		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_GET_MY_CALENDAR,nameValuePairs);
		try {
			// Obteins JSON Array
			if(json != null){
			races = json.getJSONArray(JSONTags.TAG_CALENDAR_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < races.length(); i++) {
				r = new Race();
				JSONObject c = races.getJSONObject(i);
				r.raceID = c.getInt(JSONTags.TAG_RACE_ID);
				r.name = c.getString(JSONTags.TAG_NAME);
				r.date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(c.getString((JSONTags.TAG_DATE))); ;
				r.distance = c.getString(JSONTags.TAG_DISTANCE);
				r.location = c.getString(JSONTags.TAG_LOCATION);
				r.country = c.getString(JSONTags.TAG_COUNTRY);
				r.countryCode = c.getString(JSONTags.TAG_COUNTRY_CODE);
				r.startTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).parse(c.getString((JSONTags.TAG_TIME))); 
				r.cost = c.getString(JSONTags.TAG_COST);
				r.kit = c.getString(JSONTags.TAG_KIT);
				r.registration = c.getString(JSONTags.TAG_REGISTRATION);
				r.awards = c.getString(JSONTags.TAG_AWARDS);
				r.description = c.getString(JSONTags.TAG_DESCRIPTION);
				r.benefit = c.getInt(JSONTags.TAG_BENEFIT);
				r.status = c.getString(JSONTags.TAG_STATUS);
				r.sponsors = c.getString(JSONTags.TAG_SPONSORS);
				r.latitude = c.getDouble(JSONTags.TAG_LATITUDE);
				r.longitude = c.getDouble(JSONTags.TAG_LONGITUDE);
				r.points =  c.getDouble(JSONTags.TAG_POINTS);
				r.going = c.getInt(JSONTags.TAG_GOING);
				r.dateCreated = c.getString(JSONTags.TAG_DATE_CREATED);
				r.dateModified = c.getString(JSONTags.TAG_DATE_MODIFIED);
				r.organizerID = c.getInt(JSONTags.TAG_ORGNIZER_ID);
				r.categoryID = c.getInt(JSONTags.TAG_CATEGORY_ID);
				MenuMyRacesFragment.races.add(r);
			}
			
			}else{
				//no hay data :(
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {

		MenuMyRacesFragment.setCustomResourceForDates();
		MenuMyRacesFragment.pBar.setIndeterminate(false);
		MenuMyRacesFragment.pBar.setVisibility(ProgressBar.GONE);
	}
}
