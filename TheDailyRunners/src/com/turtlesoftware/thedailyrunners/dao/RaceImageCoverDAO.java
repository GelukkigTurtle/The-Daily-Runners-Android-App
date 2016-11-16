package com.turtlesoftware.thedailyrunners.dao;

import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.turtlesoftware.thedailyrunners.beans.RaceImage;
import com.turtlesoftware.thedailyrunners.fragments.RaceInfoTabFragment;
import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class RaceImageCoverDAO extends AsyncTask<String, String, Void> {
	
	JSONArray images = null;
	private RaceInfoTabFragment mFragment;
	
	
    public RaceImageCoverDAO (RaceInfoTabFragment fragment){
    	mFragment = fragment;
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		

	}
	@Override
	protected Void  doInBackground(String... args) {
		RaceImage r =  null;
		RaceInfoTabFragment.lstImages = new ArrayList<RaceImage>();
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("race_id",args[0]));
		// Obteins JSON from URL
		

		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_RACE_COVER_IMAGES,nameValuePairs);
		try {
			// Obteins JSON Array

			images = json.getJSONArray(JSONTags.TAG_RACE_IMAGES_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < images.length(); i++) {
				r = new RaceImage();
				JSONObject c = images.getJSONObject(i);
				r.photo = c.getString(JSONTags.TAG_PHOTO);
				r.description = c.getString(JSONTags.TAG_IMG_DESCRIPTION);
				r.type = c.getString(JSONTags.TAG_TYPE);
				r.raceID = c.getString(JSONTags.TAG_RACE_REF_ID);
				RaceInfoTabFragment.lstImages.add(r);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {
		RaceInfoTabFragment.setData(mFragment);
		
		
	}

}
