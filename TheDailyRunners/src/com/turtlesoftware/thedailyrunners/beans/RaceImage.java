package com.turtlesoftware.thedailyrunners.beans;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * @author Freddy Ayala
 * @since 24 august 2014
 * 
 * This class bean reference to DB table 'RB_PHOTO_RACE'
 *
 */
public class RaceImage implements Parcelable{
	
	public int photoraceID;
	public String photo;
	public String type;
	public String description;
	public String dateCreated;
	public String raceID;
	
	@Override
	public String toString() {
		return  "url: "
				+ photo;
		
	}
	public RaceImage() {
		super();

	}
	public RaceImage(Parcel in){
		readFromParcel(in);
	}
	

	public RaceImage(int photoraceID, String photo, String type,
			String description, String dateCreated, String raceID) {
		super();
		this.photoraceID = photoraceID;
		this.photo = photo;
		this.type = type;
		this.description = description;
		this.dateCreated = dateCreated;
		this.raceID = raceID;
	}
	private void readFromParcel(Parcel in){
		photoraceID = in.readInt();
		photo = in.readString();
		type =in.readString();
		description = in.readString();
		dateCreated = in.readString();
		raceID = in.readString();
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
		dest.writeString(raceID);
		dest.writeString(photo);
		dest.writeString(description);
		dest.writeInt(photoraceID);
		dest.writeString(dateCreated);
		dest.writeString(type);
		
	}

}
