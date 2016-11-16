package com.turtlesoftware.thedailyrunners.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.turtlesoftware.thedailyrunners.beans.News;
import com.turtlesoftware.thedailyrunners.fragments.NewsFragment;
import com.turtlesoftware.thedailyrunners.tools.JSONParser;
import com.turtlesoftware.thedailyrunners.tools.JSONTags;
import com.turtlesoftware.thedailyrunners.tools.URLinks;

public class NewsDAO extends AsyncTask<String, String, Void> {
	
	JSONArray jNews = null;

	
	
    public NewsDAO (Context context){
    }
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		NewsFragment.pBar.setIndeterminate(true);
		NewsFragment.pBar.setVisibility(ProgressBar.VISIBLE);


	}
	@Override
	protected Void doInBackground(String... args) {
		
        News r =  null;
        NewsFragment.lstNews = new ArrayList<News>();
		JSONParser jParser = new JSONParser();
		// Obteins JSON from URL
		JSONObject json = jParser.getJSONFromUrl(URLinks.URL_ALL_NEWS,null);
		try {
			// Obteins JSON Array

			jNews = json.getJSONArray(JSONTags.TAG_NEWS_ARRAY);
			// Saving item JSON in Variable
			for (int i = 0; i < jNews.length(); i++) {
				r = new News();
				JSONObject c = jNews.getJSONObject(i);
				r.title = c.getString(JSONTags.TAG_NEWS_TITLE);
				r.dateCreated = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(c.getString((JSONTags.TAG_NEWS_DATE))); ;
				r.description = c.getString(JSONTags.TAG_NEWS_DESCRIPTION);
				r.url = c.getString(JSONTags.TAG_NEWS_URL);
				r.photo = c.getString(JSONTags.TAG_NEWS_PHOTO);
				 NewsFragment.lstNews.add(r);
			}
	
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		//return lstNews;
		return null;
	}

	@Override
	protected void onPostExecute(Void json) {
		NewsFragment.mCachedData.lst = (ArrayList<News>) NewsFragment.lstNews;
		NewsFragment.setAdapter();
		
		NewsFragment.pBar.setIndeterminate(false);
		NewsFragment.pBar.setVisibility(ProgressBar.GONE);
	}

}
