package com.turtlesoftware.thedailyrunners.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.dao.GetMyCalendarDAO;
import com.turtlesoftware.thedailyrunners.interfaces.Item;
import com.turtlesoftware.thedailyrunners.main.MainActivity;
import com.turtlesoftware.thedailyrunners.main.MenuBaseActivity;
import com.turtlesoftware.thedailyrunners.main.RaceInfoMain;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class MenuMyRacesFragment extends Fragment {
	
	private static CaldroidFragment caldroidFragment;
    public static  List<Item> races;
	static SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	static MenuBaseActivity mMenuBase;
	public static ProgressBar pBar;
	public static Button btnRaceSelected;
	static ProgressDialog dialog;
	static Race selectedRace;
	static MenuMyRacesFragment mFragment;
	public MenuMyRacesFragment() {
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(Configuration.LOG_TAG, "onCreateView() - Calendar");

		// inflamos la vista que queremos mostrar en pantalla
		View rootView = inflater.inflate(R.layout.menu_myraces, container, false);
		// inflamos sus componentes
		pBar = (ProgressBar) rootView.findViewById(R.id.loading_time_progress_bar_calendar);
		btnRaceSelected = (Button) rootView.findViewById(R.id.btnRaceSelected);
				
		mMenuBase = (MenuBaseActivity) getActivity();
		mFragment = MenuMyRacesFragment.this;
		MenuBaseActivity.actionBar.setTitle(getString(R.string.title_calendar));

		// If Activity is created after rotation
		if (savedInstanceState != null) {
			//caldroidFragment.restoreStatesFromKey(savedInstanceState,"CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			
		}
		
		
		
		
	
		return rootView;
	}
	public static boolean isFragmentUIActive() {
	    return mFragment.isAdded() && !mFragment.isDetached() && !mFragment.isRemoving();
	}
	
	public static void setCustomResourceForDates() {
		
		if(isFragmentUIActive()){

		// color red all races days
		Item item = null;
		if (races != null) {
			if (caldroidFragment != null) {
				Iterator<Item> iRaces = races.iterator();
				while (iRaces.hasNext()) {
					item = (Item) iRaces.next();
					Race r = (Race) item;
					setDateBackgroundColor(r);
					
				}
			}

		}
		
		// Attach to the activity
		FragmentTransaction t = mMenuBase.getSupportFragmentManager()
				.beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();
		
		btnRaceSelected.setVisibility(View.VISIBLE);
		btnRaceSelected.setText(mMenuBase.getApplicationContext().getString(R.string.calendar_button));
		btnRaceSelected.setEnabled(false);
		}

	}
	
	public static void setDateBackgroundColor(Race r){
        String today = formater.format(new Date());
        String raceDate = formater.format(r.date);
		
		String result = "";
		//compares the date of the race f1 with today f2
		result = Tools.dateCompare(raceDate,today);
		if(result.equals("f1<f2")){
			caldroidFragment.setBackgroundResourceForDate(R.color.green, r.date);
			caldroidFragment.setTextColorForDate(R.color.white,r.date);
		}
		if(result.equals("f1>f2")){
			caldroidFragment.setBackgroundResourceForDate(R.color.base_red, r.date);
			caldroidFragment.setTextColorForDate(R.color.white,r.date);
		}
		if(result.equals("f1=f2")){
			caldroidFragment.setBackgroundResourceForDate(R.color.blue, r.date);
			caldroidFragment.setTextColorForDate(R.color.white,r.date);
		}
		
	}
	
	public void getSelectedDate(Date dateSelected){
		Item item = null;
		if (races != null) {
			if (caldroidFragment != null) {
				Iterator<Item> iRaces = races.iterator();
				while (iRaces.hasNext()) {
					
					item = (Item) iRaces.next();
					Race r = (Race) item;
					String selected = formater.format(dateSelected);
				    String raceDate = formater.format(r.date);
				   
					String result = "";
				    //compares the date of the race f1 with selected race f2
			        result = Tools.dateCompare(raceDate,selected);
			       
			        if(result.equals("f1=f2")){
			    		btnRaceSelected.setText(r.name);
			    		btnRaceSelected.setEnabled(true);
			    		selectedRace = r;
			    		btnRaceSelected.setOnClickListener(race_click);
			    		return;
					}else{
						btnRaceSelected.setText(mMenuBase.getApplicationContext().getString(R.string.calendar_button));
						btnRaceSelected.setEnabled(false);
					}
					
				}
			}

		}
	}
	
	public static void  openRaceInfo(Race race){
		
		Intent intent = new Intent(mMenuBase.getApplicationContext(),RaceInfoMain.class);
		Bundle b = new Bundle();
		b.putParcelable(BundlesKeys.BUNDLE_INFO_RACE , race);
		intent.putExtras(b);
		//return intent;
		mMenuBase.startActivity(intent);
	}

	static OnClickListener race_click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (dialog != null) {
				dialog.dismiss();
			}
			dialog = ProgressDialog.show(mMenuBase, "", mMenuBase.getApplicationContext().getString(R.string.v_loading),true);
			openRaceInfo(selectedRace);
			
		}

		
	};
	
	/**
	 * Save current states of the Caldroid here
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		/*if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

		if (dialogCaldroidFragment != null) {
			dialogCaldroidFragment.saveStatesToKey(outState,
					"DIALOG_CALDROID_SAVED_STATE");
		}*/
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// Setup caldroid fragment
				caldroidFragment = new CaldroidFragment();
				
				Bundle args = new Bundle();
				Calendar cal = Calendar.getInstance();
				args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
				args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
				args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
				args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

				// Uncomment this to customize startDayOfWeek
				// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
				// CaldroidFragment.TUESDAY); // Tuesday
				caldroidFragment.setArguments(args);
				
				// Setup listener
				final CaldroidListener listener = new CaldroidListener() {

					@Override
					public void onSelectDate(Date date, View view) {
					//compares selected date with all my races dates array
					  getSelectedDate(date);

					}

					@Override
					public void onChangeMonth(int month, int year) {
						//String text = "month: " + month + " year: " + year;
						
					}

					@Override
					public void onLongClickDate(Date date, View view) {
						
					}

					@Override
					public void onCaldroidViewCreated() {
						if (caldroidFragment.getLeftArrowButton() != null) {
							
						}
					}

				};

				// Setup Caldroid
				caldroidFragment.setCaldroidListener(listener);
				
		if (MainActivity.account != null) {
			new GetMyCalendarDAO(getActivity())
					.execute(MainActivity.account.userID);
		} else {
			// show a normal calendar
			// Attach to the activity
			FragmentTransaction t = mMenuBase.getSupportFragmentManager()
					.beginTransaction();
			t.replace(R.id.calendar1, caldroidFragment);
			t.commit();

		}

		if (dialog != null) {
			dialog.dismiss();
		}
	}

}

