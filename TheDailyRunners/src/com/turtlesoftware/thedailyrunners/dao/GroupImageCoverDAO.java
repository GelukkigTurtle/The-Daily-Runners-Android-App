package com.turtlesoftware.thedailyrunners.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.turtlesoftware.thedailyrunners.beans.GroupImage;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class GroupImageCoverDAO extends AsyncTask<String, String, List<GroupImage>> {
	
	JSONArray images = null;
	List<GroupImage> groupImage;
	
	
    public GroupImageCoverDAO (Context context){
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		

	}
	@Override
	protected List<GroupImage> doInBackground(String... args) {
		GroupImage r =  null;
		groupImage = new ArrayList<GroupImage>();
		JSONParser jParser = new JSONParser();
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("group_id",args[0]));
		// Obteins JSON from URL
		Log.d(Configuration.LOG_TAG, "doInBackground() "+URLinks.URL_GROUP_COVER_IMAGES+" ID: "+ args[0] );

		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_GROUP_COVER_IMAGES,nameValuePairs);
		try {
			// Obteins JSON Array

			images = json.getJSONArray(JSONTags.TAG_GROUP_IMAGES_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < images.length(); i++) {
				r = new GroupImage();
				JSONObject c = images.getJSONObject(i);
				r.photo = c.getString(JSONTags.TAG_GROUP_IMG);
				r.description = c.getString(JSONTags.TAG_IMG_GROUP_DESCRIPTION);
				r.type = c.getString(JSONTags.TAG_GROUP_TYPE);
				r.groupID = c.getString(JSONTags.TAG_GROUP_REF_ID);
				groupImage.add(r);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return groupImage;
	}

	@Override
	protected void onPostExecute(List<GroupImage> json) {
		
		
	}

}

