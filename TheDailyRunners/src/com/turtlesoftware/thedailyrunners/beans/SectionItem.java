package com.turtlesoftware.thedailyrunners.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.turtlesoftware.thedailyrunners.interfaces.Item;

public class SectionItem implements Parcelable,Item{

	public String title;
	
	@Override
	public String toString() {
		return  title;
	}
	
	public SectionItem() {
		super();

	}
	public SectionItem(Parcel in){
		readFromParcel(in);
	}
	
	
	@Override
	public boolean isSection() {
		return true;
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		
	}
	private void readFromParcel(Parcel in){
		title = in.readString();
		
	}
	public static final Parcelable.Creator<SectionItem> CREATOR = new Parcelable.Creator<SectionItem>() {
		 @Override
		public SectionItem createFromParcel(Parcel in) {
			return new SectionItem(in);
		}
		 @Override
		public SectionItem[] newArray(int size) {
			return new SectionItem[size];
		}
	};

}
