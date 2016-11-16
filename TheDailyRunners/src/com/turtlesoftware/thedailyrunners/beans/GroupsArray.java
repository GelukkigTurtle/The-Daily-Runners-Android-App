package com.turtlesoftware.thedailyrunners.beans;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupsArray implements Parcelable{
	
	 public ArrayList<Group> lst;

	@Override
	public String toString() {
		return lst.toString();
	}

	public GroupsArray() {
		super();

	}

	public GroupsArray(Parcel in) {
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
		lst = in.readArrayList(GroupsArray.class.getClassLoader());
		
	}
	public static final Parcelable.Creator<GroupsArray> CREATOR = new Parcelable.Creator<GroupsArray>() {
		@Override
		public GroupsArray createFromParcel(Parcel in) {
			return new GroupsArray(in);
		}
		@Override
		public GroupsArray[] newArray(int size) {
			return new GroupsArray[size];
		}
	};

}