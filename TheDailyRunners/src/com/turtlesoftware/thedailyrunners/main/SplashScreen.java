package com.turtlesoftware.thedailyrunners.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.entities.Profile.Properties;
import com.sromku.simple.fb.listeners.OnProfileListener;
import com.turtlesoftware.thedailyrunners.beans.Account;
import com.turtlesoftware.thedailyrunners.main.util.SystemUiHider;
import com.turtlesoftware.thedailyrunners.tools.Configuration;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashScreen extends Activity {
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    public static SimpleFacebook mSimpleFacebook;
    public static Account account;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
      //for the error unmarshalling
        if(savedInstanceState != null)
        savedInstanceState.setClassLoader(getClass().getClassLoader());
 
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
//        new PrefetchData().execute();
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
        .add(Properties.EMAIL)
        .build();   
         mSimpleFacebook.getProfile(properties, onProfileListener);


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

    

 
}
