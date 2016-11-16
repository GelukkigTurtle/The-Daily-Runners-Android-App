package com.turtlesoftware.thedailyrunners.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.beans.RaceImage;
import com.turtlesoftware.thedailyrunners.dao.RaceImageCoverDAO;
import com.turtlesoftware.thedailyrunners.main.FullScreenViewActivity;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.main.RaceInfoMain;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;
/**
 * @author Freddy Ayala
 * @since August 2014
 * Controller of  fragment and sub components of Race Tab , race_info_front.xml	
 */
public class RaceInfoTabFragment extends Fragment implements BaseSliderView.OnSliderClickListener{
	
	
	private static SliderLayout mDemoSlider;
	public static List<RaceImage> lstImages;
	static HashMap<String, String> url_maps;
	public static RaceInfoMain mRaceInfoMain;

	public static RaceInfoTabFragment newInstance() {
		RaceInfoTabFragment frag = new RaceInfoTabFragment();
		Bundle args = frag.getArguments();
		if(args == null)
			args = new Bundle();
		frag.setArguments(args);
		return frag;
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 Race race = RaceInfoMain.raceInfoBean;
		 mRaceInfoMain = (RaceInfoMain) getActivity();
		 // inflamos la vista que queremos mostrar en pantalla
		 View rootView  = inflater.inflate(R.layout.race_info_front, container, false);
		 
		 
		 mDemoSlider = (SliderLayout) rootView.findViewById(R.id.slider);
		 url_maps = new HashMap<String, String>();
		 getRaceImagesURL(RaceInfoTabFragment.this,race.raceID);
		
		 
		 RaceInfoItemHolder holder = new RaceInfoItemHolder();
		 holder.txtRaceInfoTitle  = (TextView) rootView.findViewById(R.id.txt_race_info_name);
		 holder.txtRaceInfoPrice  = (TextView) rootView.findViewById(R.id.txt_race_info_price);
		 holder.txtRaceInfoLocation  = (TextView) rootView.findViewById(R.id.txt_race_info_location);
		 holder.txtRaceInfoDate  = (TextView) rootView.findViewById(R.id.txt_race_info_day);
	
		 holder.txtRaceInfoTitle.setText(race.name);
		 holder.txtRaceInfoPrice.setText(race.cost);
		 holder.txtRaceInfoLocation.setText(race.location);
		 holder.txtRaceInfoDate.setText(Configuration.raceinfodateformat.format(race.date)); 
		 
		
		 Tools.applyFont(getActivity(), rootView, Configuration.GENERAL_FONT_NAME);
		
		return rootView;
	}
	

	private static void getRaceImagesURL(RaceInfoTabFragment fragment,Integer raceID) {
		Log.d(Configuration.LOG_TAG, "getRaceImages()");

		new RaceImageCoverDAO(fragment).execute(raceID.toString());


	}
	
	@SuppressLint("UseValueOf") public static void setData(RaceInfoTabFragment parent){
		
		RaceImage raceImages = null;
		if(lstImages != null){
			for (int i = 0; i < lstImages.size(); i++) {
				raceImages = lstImages.get(i);
				url_maps.put(new Integer(i+1).toString(), raceImages.photo);
	
			}
		}
		
		 for(String name : url_maps.keySet()){
			 DefaultSliderView  textSliderView = new DefaultSliderView (mRaceInfoMain);
	            // initialize a SliderLayout
	            textSliderView
	                    .description("")
	                    .image(url_maps.get(name))
	                    .setScaleType(BaseSliderView.ScaleType.Fit)
	                    .setOnSliderClickListener(parent);

	            //add your extra information
	            textSliderView.getBundle()
	                    .putString("extra",name);

	           mDemoSlider.addSlider(textSliderView);
	        }
		    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
	        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
	        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
	        mDemoSlider.setDuration(6000);
	}
	
	
	

	static class RaceInfoItemHolder {
		 TextView txtRaceInfoTitle;
		 TextView txtRaceInfoPrice;
		 TextView txtRaceInfoLocation;
		 TextView txtRaceInfoDate;
	}

	@Override
	public void onSliderClick(BaseSliderView slider) {
		ArrayList<String> img  = new ArrayList<String>();
		img.add(slider.getUrl());
		Log.d(Configuration.LOG_TAG, "URL() "+ slider.getUrl());
		Intent intent = new Intent(slider.getContext(),FullScreenViewActivity.class);
		Bundle b = new Bundle();
		b.putStringArrayList(BundlesKeys.BUNDLE_RACE_IMAGE , img);
		intent.putExtras(b);
		mRaceInfoMain.startActivity(intent);
		
	}
	
	

}
