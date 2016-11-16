package com.turtlesoftware.thedailyrunners.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable{
	
	public String userID;
	public String userName;
	public String email;

	
	public Account() {
		super();

	}
	
	
	public Account(String userID, String userName ,String email) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.email = email;
	}


	public Account(Parcel in){
		readFromParcel(in);
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userID);
		dest.writeString(userName);
		dest.writeString(email);
		
	}
	private void readFromParcel(Parcel in){
		userID = in.readString();
		userName = in.readString();
		email = in.readString();
	}

}
