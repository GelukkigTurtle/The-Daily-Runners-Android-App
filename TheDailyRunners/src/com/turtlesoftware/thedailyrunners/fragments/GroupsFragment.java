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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.adapters.GroupArrayAdaptor;
import com.turtlesoftware.thedailyrunners.beans.Group;
import com.turtlesoftware.thedailyrunners.beans.GroupsArray;
import com.turtlesoftware.thedailyrunners.dao.GroupDAO;
import com.turtlesoftware.thedailyrunners.main.GroupInfoMain;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

/**
 * @author Freddy Ayala
 * @since 2014
 * Controller of Fragment and sub components of Groups Tab
 */


public class GroupsFragment extends Fragment{

	private static ListView lvGroups;	
	static View rootView;
	public static  GroupArrayAdaptor groupAdapter;	
	public static GroupsArray mCachedData = new GroupsArray();
	static MainActivity mMainActivity;
	static ProgressDialog dialog;
	public static ProgressBar pBar;
	public static List<Group> lstGroups;
	public static GroupsFragment newInstance(){
		GroupsFragment frag = new GroupsFragment();
		
		return frag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(true);

	    if ((savedInstanceState != null) && savedInstanceState.containsKey(BundlesKeys.BUNDLE_GROUPS_CACHE)) {
	        mCachedData = savedInstanceState.getParcelable(BundlesKeys.BUNDLE_GROUPS_CACHE);
	    }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		// inflamos la vista que queremos mostrar en pantalla
	    rootView = inflater.inflate(R.layout.groups_layout, container, false);
		// inflamos sus componentes
		pBar = (ProgressBar) rootView
				.findViewById(R.id.loading_time_progress_bar_group);
		
		mMainActivity = (MainActivity) getActivity();

		
		
		return rootView;
	}
	
	public static void setDataList(){
		// datos de groups
		
		lvGroups = (ListView) rootView.findViewById(R.id.lvGroups);
		if (mCachedData.lst == null) {
			Log.d(Configuration.LOG_TAG, "I make the Group request");
			getGroupsData();
			// After download is finished, you put somewhere:
			
			mCachedData.lst = (ArrayList<Group>) lstGroups;
		} else {
			Log.d(Configuration.LOG_TAG, "Cool, the data Group is cached");

		}
		
		setAdapter();
	}
	public static void setAdapter(){
		if(mCachedData.lst != null ){
			// iniciamos adpatador
			groupAdapter = new GroupArrayAdaptor(mMainActivity,R.layout.groups_listview_custom_row, mCachedData.lst);
			lvGroups.setAdapter(groupAdapter);
			// evento on Click
		    lvGroups.setOnItemClickListener(group_click);
		}
	}
	
	private static void getGroupsData() {
		Log.d(Configuration.LOG_TAG, "getGroupsData()");
		
			if(Tools.isOnline(mMainActivity)){
				 new GroupDAO(mMainActivity).execute();

				 if(lstGroups == null || lstGroups.size() < 0){
					 Tools.refreshList(mMainActivity);
				}
				
			}else{
				Toast.makeText(mMainActivity, mMainActivity.getApplicationContext().getString(R.string.v_no_internet), Toast.LENGTH_LONG).show();

			}
			
	
	}
	
	static OnItemClickListener group_click = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			if (dialog != null) {
				dialog.dismiss();
			}
			 dialog = ProgressDialog.show(mMainActivity, "", mMainActivity.getApplicationContext().getString(R.string.v_loading), true);

			openGroupInfo(position, id,arg1);
			
		}
	};
	
	public static void  openGroupInfo(int position, long id,View view){
		Intent intent = new Intent(view.getContext(),GroupInfoMain.class);
		Bundle b = new Bundle();
		b.putParcelable(BundlesKeys.BUNDLE_INFO_GROUP , (Group) mCachedData.lst.get(position));
		intent.putExtras(b);
		mMainActivity.startActivity(intent);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		 // You put the content of your list, which is this.mCachedData, in the state
	    outState.putParcelable(BundlesKeys.BUNDLE_GROUPS_CACHE, mCachedData);
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
	    super.onCreateOptionsMenu(menu,inflater);
	}
}