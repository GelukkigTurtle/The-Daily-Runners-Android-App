package com.turtlesoftware.thedailyrunners.beans;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsArray implements Parcelable{
	
	 public ArrayList<News> lst;

	@Override
	public String toString() {
		return lst.toString();
	}

	public NewsArray() {
		super();

	}

	public NewsArray(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(lst);
		
	}
	@SuppressWarnings("unchecked")
	private void readFromParcel(Parcel in){
		lst = in.readArrayList(NewsArray.class.getClassLoader());
		
	}
	public static final Parcelable.Creator<NewsArray> CREATOR = new Parcelable.Creator<NewsArray>() {
		@Override
		public NewsArray createFromParcel(Parcel in) {
			return new NewsArray(in);
		}
		@Override
		public NewsArray[] newArray(int size) {
			return new NewsArray[size];
		}
	};

}