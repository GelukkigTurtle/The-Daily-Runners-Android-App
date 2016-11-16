package com.turtlesoftware.thedailyrunners.tools;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender.SendIntentException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.turtlesoftware.thedailyrunners.interfaces.OnLocationListener;

public class LocationSupport implements GooglePlayServicesClient.ConnectionCallbacks, 
								GooglePlayServicesClient.OnConnectionFailedListener {

	private Context context;
	
	private LocationManager mLocationManager;
	private LocationClient mLocationClient;
	private OnLocationListener locationListener;
	
	public LocationSupport(Context context, OnLocationListener locationListener){
		this.locationListener = locationListener;
		this.context = context;
		mLocationClient = new LocationClient(context, this, this);
		mLocationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public void connect(){
		mLocationClient.connect();
	}
	
	public void disconnect(){
		mLocationClient.disconnect();
	}
	
	public void setStartLocation(){
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, mLocationListener);
	}
	
	

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		
		if(result.hasResolution()){
			
			try {
				result.startResolutionForResult((Activity) context, LocationTools.CONNECTION_FAILURE_RESOLUTION_REQUEST);
			} catch (SendIntentException e) {
				e.printStackTrace();
			}
			
		}else{
			
		}
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		
		
		mLocationClient.getLastLocation();
		
	}

	@Override
	public void onDisconnected() {
		return;
		
	}
	
	LocationListener mLocationListener = new LocationListener() {
		
		@Override
		 public void onLocationChanged(Location location) {
	        
			if(locationListener!=null){
				locationListener.onLocationResponse(location);
			}
			mLocationManager.removeUpdates(mLocationListener);
	        
	    }
	 
	    public void onProviderDisabled(String provider){
	    	Toast.makeText(context, "Disconnected. Please re-connect", Toast.LENGTH_LONG).show();
	    }
	 
	    public void onProviderEnabled(String provider){
	    	
	    }
	 
	    public void onStatusChanged(String provider, int status, Bundle extras){
	        
	    }
	};

	public OnLocationListener getOnLocationListener() {
		return locationListener;
	}

	public void setOnLocationListener(OnLocationListener locationListener) {
		this.locationListener = locationListener;
	}

	public LocationManager getLocationManager() {
		return mLocationManager;
	}

	public void setLocationManager(LocationManager mLocationManager) {
		this.mLocationManager = mLocationManager;
	}
	
}
