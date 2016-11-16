package com.turtlesoftware.thedailyrunners.beans;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.turtlesoftware.thedailyrunners.interfaces.Item;

public class ItemsArray implements Parcelable{
	
	 public ArrayList<Item> lst;

	@Override
	public String toString() {
		return lst.toString();
	}

	public ItemsArray() {
		super();

	}

	public ItemsArray(Parcel in) {
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
		lst = in.readArrayList(ItemsArray.class.getClassLoader());

		
	}
	public static final Parcelable.Creator<ItemsArray> CREATOR = new Parcelable.Creator<ItemsArray>() {
		@Override
		public ItemsArray createFromParcel(Parcel in) {
			return new ItemsArray(in);
		}
		@Override
		public ItemsArray[] newArray(int size) {
			return new ItemsArray[size];
		}
	};

}
