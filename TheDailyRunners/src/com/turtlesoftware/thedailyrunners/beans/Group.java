package com.turtlesoftware.thedailyrunners.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.turtlesoftware.thedailyrunners.interfaces.Item;

public class Group implements Parcelable, Item {
	
	public String groupID;
	public String name;
	public String location;
	public String schedule;
	public String description;
	public String facebook;
	public String twitter;
	public String email;
	public String photo;
	public String dateCreated;
	public String dateModified;

	public Group() {
		super();

	}
	public Group(Parcel in){
		readFromParcel(in);
	}
	
	

	
	public Group(String groupID, String name, String location, String schedule,
			String description, String facebook, String twitter, String email,
			String photo, String dateCreated, String dateModified) {
		super();
		this.groupID = groupID;
		this.name = name;
		this.location = location;
		this.schedule = schedule;
		this.description = description;
		this.facebook = facebook;
		this.twitter = twitter;
		this.email = email;
		this.photo = photo;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(groupID);
		dest.writeString(name);
		dest.writeString(location);
		dest.writeString(schedule);
		dest.writeString(description);
		dest.writeString(facebook);
		dest.writeString(twitter);
		dest.writeString(email);
		dest.writeString(photo);
		dest.writeString(dateCreated);
		dest.writeString(dateModified);
		
	}
	private void readFromParcel(Parcel in){
		groupID = in.readString();
		name = in.readString();
		location = in.readString();
		schedule = in.readString();
		description = in.readString();
	    facebook = in.readString();
	    twitter = in.readString();
	    email = in.readString();
	    photo = in.readString();
	    dateCreated = in.readString();
	    dateModified = in.readString();
	}
	
	public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
		@Override
		public Group createFromParcel(Parcel in) {
			return new Group(in);
		}
		@Override
		public Group[] newArray(int size) {
			return new Group[size];
		}
	};

	@Override
	public boolean isSection() {
		// TODO Auto-generated method stub
		return false;
	}

}
