package com.turtlesoftware.thedailyrunners.main;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.plus.PlusClient;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sromku.simple.fb.SimpleFacebook;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.fragments.MenuFragment;
import com.turtlesoftware.thedailyrunners.tools.Tools;

public class MenuBaseActivity extends SlidingFragmentActivity implements ConnectionCallbacks,
OnConnectionFailedListener, OnClickListener {
	
	private Fragment mContent;
	public static ActionBar actionBar;
	public static boolean active = false;
	public static SimpleFacebook mSimpleFacebook;
    public static PlusClient mPlusClient;
	
	@Override
	public void onStart() {
		super.onStart();
		active = true;
	}

	@Override
	public void onStop() {
		super.onStop();
		active = false;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data); 
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the Above View
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null) {
			mContent =  MenuFragment.contentSelected;
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, mContent).commit();
		}
		

		// set the Above View
		setContentView(R.layout.menu_content_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();

		// set the Behind View
		setBehindContentView(R.layout.sliding_menu);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
	    sm.setFadeDegree(0.35f);
	    
	    actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		setSlidingActionBarEnabled(false);
		
		//getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		 //change title app font
        Tools.changeTitleAppFont(this, MenuBaseActivity.this);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSimpleFacebook = SimpleFacebook.getInstance(this);

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

}
