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
import android.view.View;
import android.widget.ProgressBar;

import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.beans.SectionItem;
import com.turtlesoftware.thedailyrunners.fragments.RacesFragment;
import com.turtlesoftware.thedailyrunners.interfaces.Item;
import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.Tools;
import com.turtlesoftware.thedailyrunners.tools.URLinks;
/**
 * @author Freddy Ayala
 * @since 2014
 * Task  obtains data from a race with JSON
 */
public class RaceDAO extends AsyncTask<String, String,Void> {
	
	JSONArray races = null;
	private Context mContext;	
	ProgressBar progressbar;
	
    public RaceDAO (Context context){
         mContext = context;

    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		RacesFragment.pBar.setIndeterminate(true);
		RacesFragment.pBar.setVisibility(ProgressBar.VISIBLE);
		

	}
	@Override
	protected Void doInBackground(String... args) {
		Race r =  null;
		SectionItem section = null;
		RacesFragment.races = new ArrayList<Item>();
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("lat",args[1]));
		nameValuePairs.add(new BasicNameValuePair("long",args[2]));

		// Obteins JSON from URL
		JSONObject json = null;
		if(args[0].equals("all")){//get All Races posibles
			json = jParser.getJSONFromUrl(URLinks.URL_ALL_RACES,null);
		}else if(args[0].equals("custom")){ //get Races Filteres by Country and Category
			if(args[4].equals("0")){//All categories
				if(args[3].equals("N")){//National races
					json = jParser.getJSONFromUrl(URLinks.URL_COUNTRY_RACES,nameValuePairs);
				}else{//International races
					json = jParser.getJSONFromUrl(URLinks.URL_INTERNATIONAL_RACES,nameValuePairs);
				}
			}else{//Filtered By Caregory
				nameValuePairs.add(new BasicNameValuePair("cat_id",args[4]));

				if(args[3].equals("N")){//National races
					json = jParser.getJSONFromUrl(URLinks.URL_COUNTRY_RACES_FILTERED,nameValuePairs);

				}else{//International races
					json = jParser.getJSONFromUrl(URLinks.URL_INTERNATIONAL_RACES_FILTERED,nameValuePairs);

				}
			}
			


		}
		try {
			// Obteins JSON Array
			if(json != null){
			races = json.getJSONArray(JSONTags.TAG_RACE_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < races.length(); i++) {
				r = new Race();
				JSONObject c = races.getJSONObject(i);
				r.raceID = c.getInt(JSONTags.TAG_RACE_ID);
				r.name = c.getString(JSONTags.TAG_NAME);
				r.date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(c.getString((JSONTags.TAG_DATE))); 
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
				//adding a month separator
				if(Tools.monthChanged(r.date)){
					section = new SectionItem();
					section.title = Tools.getMonthName(mContext,r.date);
					RacesFragment.races.add(section);
				}

				RacesFragment.races.add(r);
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
		RacesFragment.mCachedData.lst = (ArrayList<Item>) RacesFragment.races;
		if(RacesFragment.mCachedData.lst.isEmpty() || RacesFragment.mCachedData.lst.size() < 1){
			RacesFragment.txtNotFound.setVisibility(View.VISIBLE);
			RacesFragment.imgNotFound.setVisibility(View.VISIBLE);
		}else{
			RacesFragment.txtNotFound.setVisibility(View.INVISIBLE);
			RacesFragment.imgNotFound.setVisibility(View.INVISIBLE);
		}
		RacesFragment.setAdapter();
		RacesFragment.pBar.setIndeterminate(false);
		RacesFragment.pBar.setVisibility(ProgressBar.GONE);
		
		
	}

}
