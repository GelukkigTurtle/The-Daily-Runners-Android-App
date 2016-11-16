package com.turtlesoftware.thedailyrunners.main;

import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnProfileListener;
import com.turtlesoftware.thedailyrunners.main.R;
import com.turtlesoftware.thedailyrunners.adapters.RaceTabsAdapter;
import com.turtlesoftware.thedailyrunners.beans.Race;
import com.turtlesoftware.thedailyrunners.dao.GetRaceAssistanceDAO;
import com.turtlesoftware.thedailyrunners.fragments.MenuFragment;
import com.turtlesoftware.thedailyrunners.fragments.RaceActionsFragment;
import com.turtlesoftware.thedailyrunners.tools.BundlesKeys;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.Tools;
import com.viewpagerindicator.TabPageIndicator;

public class RaceInfoMain extends SlidingFragmentActivity implements  ActionBar.TabListener {
	
	
	private ViewPager vPager;
	private RaceTabsAdapter tAdapter;
	public static Race raceInfoBean;
	protected ListFragment mFrag;
    ViewGroup tabLayout;
    public static SimpleFacebook mSimpleFacebook;
	public static int raceAssitance = 0;


	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_info_main);
      //for the error unmarshalling
        if(savedInstanceState != null)
        savedInstanceState.setClassLoader(getClass().getClassLoader());
        
        // set the Behind View
		setBehindContentView(R.layout.sliding_menu);
		android.support.v4.app.FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		mFrag = new MenuFragment();
		t.replace(R.id.menu_frame, mFrag);
		t.commit();
		
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		setSlidingActionBarEnabled(false);
		actionBar.setTitle(getString(R.string.title_race));

        vPager = (ViewPager) findViewById(R.id.race_info_pager);
        tAdapter = new RaceTabsAdapter(getSupportFragmentManager());
        vPager.setAdapter(tAdapter);
        
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.race_tab_indicator);
        indicator.setViewPager(vPager);
        
        //Change tabs title fonts
        tabLayout = (ViewGroup)indicator.getChildAt(0); //indicator is a horizontal scroll view, there will be only one root layout
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
        	  Tools.changeTabText(tabLayout,i);
		}
        //change title app font
        Tools.changeTitleAppFont(this, RaceInfoMain.this); 
        
        
        indicator.setOnPageChangeListener(pageChange);
    
        raceInfoBean = getIntent().getExtras().getParcelable(BundlesKeys.BUNDLE_INFO_RACE);
       
        if(MainActivity.account != null){
        	try {
				raceAssitance = new GetRaceAssistanceDAO().execute(MainActivity.account.userID, Integer.toString(raceInfoBean.raceID)).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }

    }
    

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// mostramos el fragmento seleccionado
		vPager.setCurrentItem(tab.getPosition());
		
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
			
		case R.id.action_race:
			RaceActionsFragment dialog = new RaceActionsFragment();
			dialog.show(getSupportFragmentManager(), "RaceActionsFragment");
			return true;
		
		}
		return super.onOptionsItemSelected(item);
	}
	
	public OnPageChangeListener pageChange = new ViewPager.OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			switch (position) {
			
			case 0:
				Log.d(Configuration.LOG_TAG, "page a");
				break;
			case 1:
				Log.d(Configuration.LOG_TAG, "page b");
				break;
			default:
				Log.d(Configuration.LOG_TAG, "page default ab");
				break;
			}
			
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	}; 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data); 
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSimpleFacebook = SimpleFacebook.getInstance(this);
		Profile.Properties properties = new Profile.Properties.Builder()
        .add(Properties.ID)
        .add(Properties.FIRST_NAME)
        .add(Properties.LAST_NAME)
        .build();   
         mSimpleFacebook.getProfile(properties, onProfileListener);


	}
	OnProfileListener onProfileListener = new OnProfileListener() {         
	    @Override
	    public void onComplete(Profile profile) {
	        
	    }

		@Override
		public void onFail(String reason) {
	        Log.i(Configuration.LOG_TAG, "Fail = " + reason);
			super.onFail(reason);
		}
	    
	    

	    /* 
	     * You can override other methods here: 
	     * onThinking(), onFail(String reason), onException(Throwable throwable)
	     */     
	};


	


}
