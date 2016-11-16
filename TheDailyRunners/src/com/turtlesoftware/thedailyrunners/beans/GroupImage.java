package com.turtlesoftware.thedailyrunners.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupImage implements Parcelable{
	
	public int photogroupID;
	public String photo;
	public String type;
	public String description;
	public String dateCreated;
	public String groupID;
	
	@Override
	public String toString() {
		return  "url: "
				+ photo;
		
	}
	public GroupImage() {
		super();

	}
	public GroupImage(Parcel in){
		readFromParcel(in);
	}
	

	public GroupImage(int photoraceID, String photo, String type,
			String description, String dateCreated, String raceID) {
		super();
		this.photogroupID = photoraceID;
		this.photo = photo;
		this.type = type;
		this.description = description;
		this.dateCreated = dateCreated;
		this.groupID = raceID;
	}
	private void readFromParcel(Parcel in){
		photogroupID = in.readInt();
		photo = in.readString();
		type =in.readString();
		description = in.readString();
		dateCreated = in.readString();
		groupID = in.readString();
	}
	public static final Parcelable.Creator<RaceImage> CREATOR = new Parcelable.Creator<RaceImage>() {
		public RaceImage createFromParcel(Parcel in) {
			return new RaceImage(in);
		}

		public RaceImage[] newArray(int size) {
			return new RaceImage[size];
		}
	};



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeString(groupID);
		dest.writeString(photo);
		dest.writeString(description);
		dest.writeInt(photogroupID);
		dest.writeString(dateCreated);
		dest.writeString(type);
		
	}

}
