package com.turtlesoftware.thedailyrunners.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.main.RaceInfoMain;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class RaceDetailsTabFragment  extends Fragment{
	
	private GoogleMap map;
	
	public static RaceDetailsTabFragment newInstance(){
		RaceDetailsTabFragment frag = new RaceDetailsTabFragment();
		Bundle args = frag.getArguments();
		if(args == null)
			args = new Bundle();
		frag.setArguments(args);
		return frag;
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//datos de carreras 
		Race race = RaceInfoMain.raceInfoBean;
		// inflamos la vista que queremos mostrar en pantalla
		View rootView  = inflater.inflate(R.layout.race_info_details, container, false);
		
		// Gets the MapView from the XML layout and creates it
		map = ((SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
	

       if(map != null){ //evaluate if google play services are update.
	        map.addMarker(new MarkerOptions().position(new LatLng(race.latitude,race.longitude)));
	
	
	        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
	        try {
	            MapsInitializer.initialize(this.getActivity());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	
	        // Updates the location and zoom of the MapView
	        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(race.latitude, race.longitude), 13);
	        map.animateCamera(cameraUpdate);
       }
		LinearLayout mother = (LinearLayout) rootView.findViewById(R.id.mother);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 10, 0, 10);
		
		View inflatedView;
	
		
		inflatedView = View.inflate(this.getActivity(), R.layout.race_info_details_row, null);
						
		((TextView) inflatedView.findViewById(R.id.txt_race_info_title_box)).setText(R.string.title_kit);
		((TextView) inflatedView.findViewById(R.id.txt_race_info_detail)).setText(race.kit);
		((ImageView) inflatedView.findViewById(R.id.imgDetailIcon)).setImageDrawable(this.getActivity().getResources().getDrawable((R.drawable.ic_kit)));
		inflatedView.setLayoutParams(lp);
		mother.addView(inflatedView);
		
		inflatedView = View.inflate(this.getActivity(), R.layout.race_info_details_row, null);
		
		((TextView) inflatedView.findViewById(R.id.txt_race_info_title_box)).setText(R.string.title_description);
		((TextView) inflatedView.findViewById(R.id.txt_race_info_detail)).setText(race.description);
		((ImageView) inflatedView.findViewById(R.id.imgDetailIcon)).setImageDrawable(this.getActivity().getResources().getDrawable((R.drawable.ic_description)));

		inflatedView.setLayoutParams(lp);
		mother.addView(inflatedView);
		
       inflatedView = View.inflate(this.getActivity(), R.layout.race_info_details_row, null);
		
		((TextView) inflatedView.findViewById(R.id.txt_race_info_title_box)).setText(R.string.title_registration);
		((TextView) inflatedView.findViewById(R.id.txt_race_info_detail)).setText(race.registration);
		((ImageView) inflatedView.findViewById(R.id.imgDetailIcon)).setImageDrawable(this.getActivity().getResources().getDrawable((R.drawable.ic_inscription)));

		inflatedView.setLayoutParams(lp);
		mother.addView(inflatedView);
		
		
		inflatedView = View.inflate(this.getActivity(), R.layout.race_info_details_row, null);
		
		((TextView) inflatedView.findViewById(R.id.txt_race_info_title_box)).setText(R.string.title_sponsors);
		((TextView) inflatedView.findViewById(R.id.txt_race_info_detail)).setText(race.sponsors);
		((ImageView) inflatedView.findViewById(R.id.imgDetailIcon)).setImageDrawable(this.getActivity().getResources().getDrawable((R.drawable.ic_sponsor)));

		inflatedView.setLayoutParams(lp);
		mother.addView(inflatedView);
		
		 Tools.applyFont(getActivity(), rootView, Configuration.GENERAL_FONT_NAME);
		

		return rootView;
	}
	



}
