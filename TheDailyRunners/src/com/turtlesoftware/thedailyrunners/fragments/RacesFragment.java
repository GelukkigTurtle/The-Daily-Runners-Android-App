package com.turtlesoftware.thedailyrunners.fragments;

import java.util.ArrayList;
import java.util.List;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.adapters.RaceArrayAdaptor;
import com.turtlesoftware.thedailyrunners.beans.ItemsArray;
import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.dao.RaceDAO;
import com.turtlesoftware.thedailyrunners.interfaces.Item;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.main.RaceInfoMain;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;


/**
 * @author Freddy Ayala
 * @since 2014
 * Controller of  fragment and sub components of Race Tab , race_layout.xml	
 */
public class RacesFragment extends Fragment{ 

    static View rootView;
    public static  List<Item> races;
    static ListView lvRaces;
	public static RaceArrayAdaptor raceAdapter;	
	public static ItemsArray mCachedData = new ItemsArray();
	static ProgressDialog dialog;
	public static ProgressBar pBar;
	public static TextView txtNotFound;
	public static ImageView imgNotFound;
	
	static MainActivity mMainActivity;
	
	private boolean loadFirstTime = true;
	
	public static RacesFragment newInstance(String text){
		RacesFragment frag = new RacesFragment();
		
		Bundle args = frag.getArguments();
		if(args == null)
			args = new Bundle();
		

		frag.setArguments(args);
		
		return frag;
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(true);

	    if ((savedInstanceState != null) && savedInstanceState.containsKey(BundlesKeys.BUNDLE_CACHE)) {
	        mCachedData = savedInstanceState.getParcelable(BundlesKeys.BUNDLE_CACHE);
	    }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		// inflamos la vista que queremos mostrar en pantalla
		rootView = inflater.inflate(R.layout.race_layout, container, false);
		// inflamos sus componentes
		pBar = (ProgressBar) rootView.findViewById(R.id.loading_time_progress_bar_race);
		txtNotFound = (TextView) rootView.findViewById(R.id.txtNotFound);
		imgNotFound = (ImageView) rootView.findViewById(R.id.notFoundPicture);
        mMainActivity = (MainActivity) getActivity();
	   
        if(loadFirstTime){
        	mMainActivity.pageChange.onPageSelected(0); //select first page
        	loadFirstTime = false;
        }
		return rootView;
	}
	
	 static void getRaceData() {
		Log.d(Configuration.LOG_TAG, "getRaceData()");

			if(Tools.isOnline(mMainActivity)){
				
				if(MainActivity.countryLat !=null && MainActivity.countryLong !=null){//Filtered by Country and Category
					

			    new RaceDAO(mMainActivity).execute("custom",MainActivity.countryLat,MainActivity.countryLong,MainActivity.localSelection,MainActivity.categoryCode);
				
				}else{//All Races
				new RaceDAO(mMainActivity).execute("all",null,null,null);
				}
				if(races == null || races.size() < 0){
				 Tools.refreshList(mMainActivity);
			}
				
			}else{
				Toast.makeText(mMainActivity, mMainActivity.getApplicationContext().getString(R.string.v_no_internet), Toast.LENGTH_LONG).show();
			}
		
	}
	
	public static void setDataList(boolean filtered){
		//datos de carreras 
		if(filtered){
		  mCachedData.lst.clear();
		  mCachedData.lst = null;
		  raceAdapter.notifyDataSetChanged();
		}
		lvRaces = (ListView) rootView.findViewById(R.id.lvRaces);
		if (mCachedData.lst == null) {
			Log.d(Configuration.LOG_TAG, "I make the request");
			
			getRaceData();
			// After download is finished, you put somewhere:
			mCachedData.lst = (ArrayList<Item>) races;
		} else {
			Log.d(Configuration.LOG_TAG, "Cool, the data RACE is cached");

		}

		
		setAdapter();
		
	}
	
	public static void setAdapter(){
		// iniciamos adpatador
		if (mCachedData.lst != null) {
			
			raceAdapter = new RaceArrayAdaptor(mMainActivity,
					R.layout.race_listview_custom_row, mCachedData.lst);
			lvRaces.setAdapter(raceAdapter);
			Log.d(Configuration.LOG_TAG, "Race Adapter Established");
			// evento on Click
			lvRaces.setOnItemClickListener(race_click);
			
		}
	}
	
	static OnItemClickListener race_click = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {

			if(!mCachedData.lst.get(position).isSection()){
				if (dialog != null) {
					dialog.dismiss();
				}
			 dialog = ProgressDialog.show(mMainActivity, "", mMainActivity.getApplicationContext().getString(R.string.v_loading), true);

				//loading.execute(openRaceInfo(position, id,arg1));
				openRaceInfo(position, id,arg1);
			   
			}
		}
	};
	
	public static void  openRaceInfo(int position, long id,View view){
	
		Intent intent = new Intent(view.getContext(),RaceInfoMain.class);
		Bundle b = new Bundle();
		b.putParcelable(BundlesKeys.BUNDLE_INFO_RACE , (Race) mCachedData.lst.get(position));
		intent.putExtras(b);
		//return intent;
		mMainActivity.startActivity(intent);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		 // You put the content of your list, which is this.mCachedData, in the state
	    outState.putParcelable(BundlesKeys.BUNDLE_CACHE, mCachedData);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (dialog != null) {
			dialog.dismiss();
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.refresh_main, menu);
	    inflater.inflate(R.menu.info_main, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
	

	
	
	

}
