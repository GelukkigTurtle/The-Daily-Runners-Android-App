package com.turtlesoftware.thedailyrunners.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.adapters.NewsArrayAdaptor;
import com.turtlesoftware.thedailyrunners.beans.News;
import com.turtlesoftware.thedailyrunners.beans.NewsArray;
import com.turtlesoftware.thedailyrunners.dao.NewsDAO;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.main.NewsContainerActivity;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;
/**
 * @author Freddy Ayala
 * @since 2014
 * Controller of  fragment and sub components of News Tab
 */

public class NewsFragment extends Fragment{
	
	private static ListView lvNews;	
	public static List<News> lstNews;
	static View rootView;
	public static NewsArrayAdaptor newsAdapter;	
	public static NewsArray mCachedData = new NewsArray();
	static MainActivity mMainActivity;
	public static ProgressBar pBar;
	public static NewsFragment newInstance(){
		NewsFragment frag = new NewsFragment();
		
		return frag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(true);

	    if ((savedInstanceState != null) && savedInstanceState.containsKey(BundlesKeys.BUNDLE_NEWS_CACHE)) {
	        mCachedData = savedInstanceState.getParcelable(BundlesKeys.BUNDLE_NEWS_CACHE);
	    }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflamos la vista que queremos mostrar en pantalla
	    rootView = inflater.inflate(R.layout.news_layout, container, false);
		// inflamos sus componentes

		pBar = (ProgressBar) rootView.findViewById(R.id.loading_time_progress_bar_news);
		
		mMainActivity = (MainActivity) getActivity();

		
	 		
		return rootView;
	}
	private static void getNewsData() {
		Log.d(Configuration.LOG_TAG, "getNewsData()");
	
			if(Tools.isOnline(mMainActivity)){
				//lst = (ArrayList<Item>) new NewsDAO(mMainActivity).execute();
				new NewsDAO(mMainActivity).execute();
				if(lstNews == null || lstNews.size() < 0){
					Tools.refreshList(mMainActivity);
				}
					
				
			}else{
				Toast.makeText(mMainActivity, mMainActivity.getApplicationContext().getString(R.string.v_no_internet), Toast.LENGTH_LONG).show();

			}
			
		
	}
	public static void setDataList(){
		lvNews = (ListView) rootView.findViewById(R.id.lvNews);

		if (mCachedData.lst == null) {
			Log.d(Configuration.LOG_TAG, "I make the News request");
			getNewsData();
			// After download is finished, you put somewhere:
			mCachedData.lst = (ArrayList<News>) lstNews;
		} else {
			Log.d(Configuration.LOG_TAG, "Cool, the data News is cached");

		}
		
		setAdapter();
		
		
	}
	
	public static void setAdapter(){
		if (mCachedData.lst != null) {
			// iniciamos adpatador
			newsAdapter = new NewsArrayAdaptor(mMainActivity,
					R.layout.news_listview_custom_row, mCachedData.lst);
			lvNews.setAdapter(newsAdapter);
			// evento on Click
			lvNews.setOnItemClickListener(news_click);
		}
	}
	
	static OnItemClickListener news_click = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			
				openNewsInfo(position, id,arg1);
			
		}
	};
	
	public static void  openNewsInfo(int position, long id,View view){
		Intent intent = new Intent(view.getContext(),NewsContainerActivity.class);
		Bundle b = new Bundle();
		b.putParcelable(BundlesKeys.BUNDLE_INFO_NEWS , (News) mCachedData.lst.get(position));
		intent.putExtras(b);
		mMainActivity.startActivity(intent);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		 // You put the content of your list, which is this.mCachedData, in the state
	    outState.putParcelable(BundlesKeys.BUNDLE_NEWS_CACHE, mCachedData);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.refresh_main, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
	
	
	
	

}
