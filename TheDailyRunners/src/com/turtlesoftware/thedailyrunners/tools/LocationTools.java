package com.turtlesoftware.thedailyrunners.tools;

import android.location.LocationManager;

public class LocationTools {

	
    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
	public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	
	public static boolean isServiceActivated(LocationManager manager){
		
		if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) return true;
		else return false;

	}
	
	
	
}
