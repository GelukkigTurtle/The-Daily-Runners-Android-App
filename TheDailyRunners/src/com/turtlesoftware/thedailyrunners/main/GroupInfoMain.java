package com.turtlesoftware.thedailyrunners.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.turtlesoftware.thedailyrunners.beans.Group;
import com.turtlesoftware.thedailyrunners.beans.GroupImage;
import com.turtlesoftware.thedailyrunners.dao.GroupImageCoverDAO;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class GroupInfoMain extends SlidingFragmentActivity implements BaseSliderView.OnSliderClickListener,  ActionBar.TabListener {

	public static Group groupInfoBean;
	public TextView txtGroupName;
	public TextView txtGroupDescription;
	public TextView txtGroupLocation;
	public TextView txtGroupSchedule;
	public TextView txtGroupFB;
	
	public TextView txtGroupAttrFB;
	public TextView txtGroupAttrSchedule;

    private SliderLayout mDemoSlider;
    HashMap<String,String> url_maps = new HashMap<String, String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups_info);
		
		 // set the Behind View
		setBehindContentView(R.layout.sliding_menu);
		android.support.v4.app.FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		t.commit();
		
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);

		//set action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setSlidingActionBarEnabled(false);
		actionBar.setTitle(getString(R.string.title_groups));
		
		 //Obtein Bundle data from a group
		groupInfoBean = getIntent().getExtras().getParcelable(BundlesKeys.BUNDLE_INFO_GROUP);
		loadData();
	}
	
	private void loadData(){
		txtGroupName = (TextView) findViewById(R.id.txt_group_name);
		txtGroupDescription = (TextView)  findViewById(R.id.txt_group_description);
		txtGroupFB = (TextView) findViewById(R.id.txt_group_fb);
		txtGroupLocation = (TextView) findViewById(R.id.txt_group_loctation);
		txtGroupSchedule = (TextView) findViewById(R.id.txt_group_schedule);
		
		//atributes
		txtGroupAttrSchedule = (TextView) findViewById(R.id.txt_atrr_schedule);
		txtGroupAttrFB = (TextView) findViewById(R.id.txt_atrr_fb);
		
		

		txtGroupName.setText(groupInfoBean.name);
		txtGroupDescription.setText(groupInfoBean.description);
		txtGroupLocation.setText(groupInfoBean.location);
		
		if(groupInfoBean.facebook == null || groupInfoBean.facebook.equals("") || groupInfoBean.facebook.equals("null")){
			txtGroupAttrFB.setVisibility(View.INVISIBLE);

		}else{
			txtGroupFB.setText(groupInfoBean.facebook);

		}
		
		if(groupInfoBean.schedule == null || groupInfoBean.schedule.equals("") || groupInfoBean.schedule.equals("null") ){
			txtGroupAttrSchedule.setVisibility(View.INVISIBLE);

		}else{
			txtGroupSchedule.setText(groupInfoBean.schedule);

		}
		
		 this.getImages(groupInfoBean.groupID);
		
		 mDemoSlider = (SliderLayout) findViewById(R.id.GroupSlider);
		 for(String name : url_maps.keySet()){
			 DefaultSliderView  textSliderView = new DefaultSliderView (this);
	            // initialize a SliderLayout
	            textSliderView
	                    .description("")
	                    .image(url_maps.get(name))
	                    .setScaleType(BaseSliderView.ScaleType.Fit)
	                    .setOnSliderClickListener(this);

	            //add your extra information
	            textSliderView.getBundle()
	                    .putString("extra",name);

	           mDemoSlider.addSlider(textSliderView);
	        }
		    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
	        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
	        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
	        mDemoSlider.setDuration(8000);
		
		
		//TextJustifyUtils.justify((TextView) findViewById(R.id.txt_group_description));
		Tools.applyFont(this,txtGroupName, Configuration.GENERAL_FONT_NAME);
		Tools.applyFont(this,txtGroupDescription, Configuration.GENERAL_FONT_NAME);
		Tools.applyFont(this,txtGroupFB, Configuration.GENERAL_FONT_NAME);
		//change title app font
        Tools.changeTitleAppFont(this, GroupInfoMain.this);

		
		
	}
	
	@SuppressLint("UseValueOf") private void getImages(String groupID) {
		List<GroupImage> lst = getGroupImagesURL(groupID);
		GroupImage groupImage = null;
		if(lst != null){
			for (int i = 0; i < lst.size(); i++) {
				groupImage = lst.get(i);
				url_maps.put(new Integer(i+1).toString(), groupImage.photo);
	
			}
		}

	}
	private List<GroupImage> getGroupImagesURL(String groupID) {
		Log.d(Configuration.LOG_TAG, "getGroupImages() + id "+ groupID );
		//lstRaces.clear();
		List<GroupImage> lst = null;
		try {
			
			lst = (ArrayList<GroupImage>) new GroupImageCoverDAO(this).execute(groupID).get();
			
			if(lst == null || lst.size() < 0){
				Toast.makeText(this, "No group images to show", Toast.LENGTH_LONG).show();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return lst;
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
		startActivity(intent);
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
