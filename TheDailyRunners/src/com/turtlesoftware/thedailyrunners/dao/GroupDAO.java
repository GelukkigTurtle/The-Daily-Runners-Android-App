package com.turtlesoftware.thedailyrunners.dao;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.turtlesoftware.thedailyrunners.beans.Group;
import com.turtlesoftware.thedailyrunners.fragments.GroupsFragment;
import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class GroupDAO extends AsyncTask<String, String, Void> {
	
	JSONArray jGroups = null;	
	
	ProgressBar progressbar;
    public GroupDAO (Context context){
         
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		GroupsFragment.pBar.setIndeterminate(true);
		GroupsFragment.pBar.setVisibility(ProgressBar.VISIBLE);
		
	}
	@Override
	protected Void doInBackground(String... args) {
		Group r =  null;
		GroupsFragment.lstGroups = new ArrayList<Group>();
		JSONParser jParser = new JSONParser();
		// Obteins JSON from URL
		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_ALL_GROUPS,null);
		try {
			// Obteins JSON Array

			jGroups = json.getJSONArray(JSONTags.TAG_GROUP_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < jGroups.length(); i++) {
				r = new Group();
				JSONObject c = jGroups.getJSONObject(i);
				
				r.groupID= c.getString(JSONTags.TAG_GROUP_ID);
				r.name = c.getString(JSONTags.TAG_GROUP_NAME);
				r.location = c.getString(JSONTags.TAG_GROUP_LOCATION);
				r.description = c.getString(JSONTags.TAG_GROUP_DESCRIPTION);
				r.facebook = c.getString(JSONTags.TAG_GROUP_FACEBOOK);
				r.twitter = c.getString(JSONTags.TAG_GROUP_TWITTER);
				r.email = c.getString(JSONTags.TAG_GROUP_EMAIL);
				r.photo = c.getString(JSONTags.TAG_GROUP_PHOTO);
				r.schedule = c.getString(JSONTags.TAG_GROUP_SCHEDULE);
				r.dateCreated = c.getString(JSONTags.TAG_GROUP_DATE_CREATED);
				r.dateModified = c.getString(JSONTags.TAG_GROUP_DATE_MODIFIED);
				
				GroupsFragment.lstGroups.add(r);
			}

			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {
		GroupsFragment.mCachedData.lst = (ArrayList<Group>) GroupsFragment.lstGroups;
		GroupsFragment.setAdapter();
		GroupsFragment.pBar.setIndeterminate(false);
		GroupsFragment.pBar.setVisibility(ProgressBar.GONE);	
	}

}
