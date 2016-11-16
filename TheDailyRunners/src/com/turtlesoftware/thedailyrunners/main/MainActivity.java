package com.turtlesoftware.thedailyrunners.main;

import static com.turtlesoftware.thedailyrunners.main.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.turtlesoftware.thedailyrunners.main.CommonUtilities.SENDER_ID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnProfileListener;
import com.turtlesoftware.thedailyrunners.adapters.SortExpandableAdapter;
import com.turtlesoftware.thedailyrunners.adapters.TabsAdapter;
import com.turtlesoftware.thedailyrunners.beans.Account;
import com.turtlesoftware.thedailyrunners.fragments.GalleryFragment;
import com.turtlesoftware.thedailyrunners.fragments.GroupsFragment;
import com.turtlesoftware.thedailyrunners.fragments.MenuFragment;
import com.turtlesoftware.thedailyrunners.fragments.NewsFragment;
import com.turtlesoftware.thedailyrunners.fragments.RacesFragment;
import com.turtlesoftware.thedailyrunners.interfaces.OnLocationListener;
import com.turtlesoftware.thedailyrunners.tools.Configuration;
import com.turtlesoftware.thedailyrunners.tools.ErrorServiceLocationDialog;
import com.turtlesoftware.thedailyrunners.tools.LocationSupport;
import com.turtlesoftware.thedailyrunners.tools.LocationTools;
import com.turtlesoftware.thedailyrunners.tools.Tools;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @author Freddy Ayala
 * @since 2014
 * Actividad Principal en la que se adpatan todos los fragmentos a la vista
 */
public class MainActivity extends SlidingFragmentActivity implements  ActionBar.TabListener {
	
	private ViewPager  vPager;
	private TabsAdapter tAdapter;
	protected ListFragment mFrag;
	ViewGroup tabLayout;
	
	//Sorting Menu
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
    AlertDialog dialog;
    public static String categoryCode = "0"; //default: 0 , all races no filters
    public static String localSelection = "N"; //default: N , National
	//Location
	public static Location lastLocation = null;
	public static String countryLat;
	public static String countryLong;
	public ArrayList<String> locationData;

	LocationSupport ls;
    //GCM
	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;

	// Connection detector
	ConnectionDetector cd;

	public static String name;
	public static String email;
	public static Context mcontext;
	
	public static int TAB_SELECTED;
	
	//facebook
	 public static SimpleFacebook mSimpleFacebook;
	 public static Account account;
	
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      //Get a Tracker (should auto-report)
        ((TheDailyRunners) getApplication()).getTracker(TheDailyRunners.TrackerName.APP_TRACKER);
        GoogleAnalytics.getInstance(getBaseContext()).dispatchLocalHits();
      //  ScreenViewBuilder.
        mcontext  = getApplicationContext();
        Tools.getHashCode(mcontext); //for development
		ls = new LocationSupport(this, myLocationListener);

        getLocation();
        
        prepareSortingListData();
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
        
        vPager = (ViewPager) findViewById(R.id.pager);
        tAdapter = new TabsAdapter(getSupportFragmentManager());
        
        vPager.setAdapter(tAdapter);
        
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(vPager);
        
        //Change tabs title fonts
        tabLayout = (ViewGroup)indicator.getChildAt(0); //indicator is a horizontal scroll view, there will be only one root layout
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
        	  Tools.changeTabText(tabLayout,i);
		}
        //change title app font
        Tools.changeTitleAppFont(this, MainActivity.this); 
        
        indicator.setOnPageChangeListener(pageChange);
        
        //GCM
        if(SplashScreen.account != null){
        cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			Toast.makeText(this, getApplicationContext().getString(R.string.v_no_internet), Toast.LENGTH_LONG).show();
			// stop executing code by return
			return;
		}
		
		
		//name = "Freddy";
		//email = "fredd.seb8@gmail.com";

		//	Log.d(Configuration.LOG_TAG, "se REGISTRARA CON GCM");

//			account.userID = SplashScreen.account.userID;
//			account.email = SplashScreen.account.email;
			name = SplashScreen.account.userID;
			email = SplashScreen.account.email;
			if(account != null ){
				account.userID = SplashScreen.account.userID;
				account.email = SplashScreen.account.email;
			}else{
				account = new Account();
				account.userID = SplashScreen.account.userID;
				account.email = SplashScreen.account.email;
			}
			
		
		// Make sure the device has the proper dependencies.
		GCMRegistrar.checkDevice(this);

		// Make sure the manifest was properly set - comment out this line
		// while developing the app, then uncomment it when it's ready.
		GCMRegistrar.checkManifest(this);

		
		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
		
		// Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(this);

		// Check if regid already presents
		if (regId.equals("")) {
			// Registration is not present, register now with GCM			
			GCMRegistrar.register(this, SENDER_ID);
		} else {
			// Device is already registered on GCM
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				// Skips registration.				
				//Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
				Log.d(Configuration.LOG_TAG,  "Already registered with GCM");

			} else {
				// Try to register again, but not in the UI thread.
				// It's also necessary to cancel the thread onDestroy(),
				// hence the use of AsyncTask instead of a raw thread.
				final Context context = this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// Register on our server
						// On server creates a new user
						ServerUtilities.register(context, name, email, regId);
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);
			}
		}
        
        //END GCM
		
       }
 
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.info_main, menu);
        return true;
    }


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
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
			toggle();
			return true;
			
		case R.id.action_sort:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_sort);

			ExpandableListView myList = new ExpandableListView(this);
			SortExpandableAdapter myAdapter = new SortExpandableAdapter(this, listDataHeader, listDataChild);
			myList.setAdapter(myAdapter);
			myList.setOnChildClickListener(new OnChildClickListener() {


				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {

					HashMap<String, List<String>> map = (HashMap<String, List<String>>) listDataChild;
					String header = listDataHeader.get(groupPosition);
					List<String> option = map.get(listDataHeader.get(groupPosition));
					categoryCode = Tools.getCategoryCode(option.get(childPosition));
					Toast.makeText(
							MainActivity.this, option.get(childPosition)+ " "
									+ header,
							Toast.LENGTH_SHORT).show();
					if(header.equals("Nacionales") || header.equals("National")){
						localSelection = "N";
						
					}else{
						localSelection = "I";
					}
					RacesFragment.setDataList(true);

					dialog.dismiss();
					return true;
				}
			});
			
			builder.setView(myList);
			dialog = builder.create();
			dialog.show();
			return true;
			
		case R.id.action_refresh:
			switch (TAB_SELECTED) {
			case 0:
				 RacesFragment.mCachedData.lst.clear();
				 RacesFragment.mCachedData.lst = null;
				 RacesFragment.raceAdapter.notifyDataSetChanged();
				break;
			case 1:
				 NewsFragment.mCachedData.lst.clear();
				 NewsFragment.mCachedData.lst = null;
				 NewsFragment.newsAdapter.notifyDataSetChanged();

				break;
			case 2:
				 GroupsFragment.mCachedData.lst.clear();
				 GroupsFragment.mCachedData.lst = null;
				 GroupsFragment.groupAdapter.notifyDataSetChanged();

				break;
			case 3:
				//reload page when open again
				break;
			}
			this.pageChange.onPageSelected(TAB_SELECTED);
			return true;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
	public  OnPageChangeListener pageChange = new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				
				case 0:
					Log.d(Configuration.LOG_TAG, "page 1");
					RacesFragment.setDataList(false);
					TAB_SELECTED = 0;
					break;
				case 1:
					Log.d(Configuration.LOG_TAG, "page 2");
					NewsFragment.setDataList();
					TAB_SELECTED = 1;
					break;
				case 2:
					Log.d(Configuration.LOG_TAG, "page 3");
					GroupsFragment.setDataList();
					TAB_SELECTED = 2;
					break;
				case 3:
					Log.d(Configuration.LOG_TAG, "page 4");
					GalleryFragment.setDataList();
					TAB_SELECTED = 3;
					break;

				default:
					Log.d(Configuration.LOG_TAG, "page default");
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
	
		public void getLocation(){
			if(LocationTools.isServiceActivated(ls.getLocationManager())){

				ls.setStartLocation();
				}else{
					FragmentManager fragmentManager = getSupportFragmentManager();
					ErrorServiceLocationDialog errorServiceL = new ErrorServiceLocationDialog();
					errorServiceL.show(fragmentManager, "ALERTA");
				}
			
		}
		OnLocationListener myLocationListener = new OnLocationListener() {

			@Override
			public void onLocationResponse(Location location) {		
				
				lastLocation = location;
				if(lastLocation != null){
					Log.d(Configuration.LOG_TAG, "lat: "+ String.valueOf(lastLocation.getLatitude()));
					Log.d(Configuration.LOG_TAG, "long: "+ String.valueOf(lastLocation.getLongitude()));
					countryLat = String.valueOf(lastLocation.getLatitude());
					countryLong = String.valueOf(lastLocation.getLongitude());
					//pageChange.onPageSelected(0); evaluate mcache data races
				}
				
			}
			
		};
		 /*
	     * Preparing the menu sorting list
	     */
	    private void prepareSortingListData() {
	        listDataHeader = new ArrayList<String>();
	        
	        listDataChild = new HashMap<String, List<String>>();
	        List<String> childs = new ArrayList<String>();

	        String [] mHeaders = getResources().getStringArray(R.array.menu_sorting_headers);
	        for (int i = 0; i < mHeaders.length; i++) {
				listDataHeader.add(mHeaders[i]);
			}
	        
	        String [] mChilds = getResources().getStringArray(R.array.menu_sorting_childs);
	        for (int i = 0; i < mChilds.length; i++) {
	        	childs.add(mChilds[i]);
			}
	        //Put all childs for every header
	        for (int i = 0; i < listDataHeader.size(); i++) {
				listDataChild.put(listDataHeader.get(i), childs);
				
			}
	        
	       
	    }
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK && TabsAdapter.galleryFrag != null && TabsAdapter.galleryFrag.canGoBack()) {
	        	TabsAdapter.galleryFrag.goBack();
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }

	    
	    /**
		 * Receiving push messages
		 * */
		public final  static BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				//String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
				// Waking up mobile if it is sleeping
				WakeLocker.acquire(mcontext);
				
				/**
				 * Take appropriate action on this message
				 * depending upon your app requirement
				 * For now i am just displaying it on the screen
				 * */			
				//Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
				
				// Releasing wake lock
				WakeLocker.release();
			}
		};
		
		@Override
		protected void onDestroy() {
			if (mRegisterTask != null) {
				mRegisterTask.cancel(true);
			}
			try {
				unregisterReceiver(mHandleMessageReceiver);
				GCMRegistrar.onDestroy(this);
			} catch (Exception e) {
				Log.e("UnRegister Receiver Error", "> " + e.getMessage());
			}
			super.onDestroy();
		}
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			 mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data); 
			super.onActivityResult(requestCode, resultCode, data);
		}
		
		@Override
		protected void onResume() {
		  super.onResume();

		  mSimpleFacebook = SimpleFacebook.getInstance(this);
			Profile.Properties properties = new Profile.Properties.Builder()
		    .add(Properties.ID)
	        .add(Properties.FIRST_NAME)
	        .add(Properties.LAST_NAME)
	        .add(Properties.EMAIL)
	        .add(Properties.GENDER)
	        .build();   
	         mSimpleFacebook.getProfile(properties, onProfileListener);	
	         AppEventsLogger.activateApp(this);
		}
		
		OnProfileListener onProfileListener = new OnProfileListener() {         
		    @Override
		    public void onComplete(Profile profile) {
		        account = new Account();
				account.userID = profile.getId();
				account.userName = profile.getName()+" "+profile.getLastName();
				account.email = profile.getEmail();
		        Log.i(Configuration.LOG_TAG, "My profile id = " + profile.getId());
		        Log.i(Configuration.LOG_TAG, "My profile name = " + profile.getFirstName() + " "+  profile.getLastName());
		        Log.i(Configuration.LOG_TAG, "My email = " + profile.getEmail());

				
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

		@Override
		protected void onPause() {
		  super.onPause();

		  // Logs 'app deactivate' App Event.
		  AppEventsLogger.deactivateApp(this);
		}
		
		//exit
		@Override
        public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Deseas salir de la aplicación?")
                   .setCancelable(false)
                   .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                	   public void onClick(DialogInterface dialog, int id) {

//                           Intent intent = new Intent(Intent.ACTION_MAIN);
//                           intent.addCategory(Intent.CATEGORY_HOME);
//                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                           startActivity(intent);
//                           FileCache loader = new FileCache(null);
//                           loader.clear();  
                		   Tools.deleteDirectoryTree(getCacheDir());
                           System.exit(0);
                       }
                   })
                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                       }
                   });
            AlertDialog alert = builder.create();
            alert.show();

    }


		@Override
		protected void onStart() {
			super.onStart();
			//Get an Analytics tracker to report app starts & uncaught exceptions etc.
			GoogleAnalytics.getInstance(this).reportActivityStart(this);
		}


		@Override
		protected void onStop() {
			super.onStop();
			//Stop the analytics tracking
			GoogleAnalytics.getInstance(this).reportActivityStop(this);
		}
		
		
		
    
}
