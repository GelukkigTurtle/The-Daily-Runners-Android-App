package com.turtlesoftware.thedailyrunners.beans;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.turtlesoftware.thedailyrunners.interfaces.Item;

public class News implements Parcelable, Item{
	
	public String title;
	public String description;
	public String url;
	public String photo;
	public Date dateCreated;
	
	public News() {
		super();

	}
	public News(Parcel in){
		readFromParcel(in);
	}
	
	

	public News(String title, String description, String url, String photo,
			Date dateCreated) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.photo = photo;
		this.dateCreated = dateCreated;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(description);
		dest.writeString(url);
		dest.writeString(photo);
		dest.writeSerializable(dateCreated);
		
	}
	private void readFromParcel(Parcel in){
		title = in.readString();
		description = in.readString();
		url = in.readString();
		photo = in.readString();
		dateCreated = (java.util.Date) in.readSerializable();
	}
	
	public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
		@Override
		public News createFromParcel(Parcel in) {
			return new News(in);
		}
		@Override
		public News[] newArray(int size) {
			return new News[size];
		}
	};

	@Override
	public boolean isSection() {
		// TODO Auto-generated method stub
		return false;
	}


}
