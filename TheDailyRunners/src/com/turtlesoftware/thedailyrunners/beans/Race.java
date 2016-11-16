package com.turtlesoftware.thedailyrunners.beans;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.turtlesoftware.thedailyrunners.interfaces.Item;

/**
 * @author Freddy Ayala
 * @since 16 august 2014
 * 
 * This class bean reference to DB table 'RB_RACE'
 *
 */
public class Race implements Parcelable,Item{ 
	
	
	public int raceID;
	public String name;
	public String distance;
	public String location;
	public String country;
	public String countryCode;
	public Date date;
	public Date startTime;
	public String cost;
	public String kit;
	public String registration;
	public 	String awards;
	public String description;
	public int benefit;
	public 	String status;
	public String sponsors;
	public Double latitude;
	public Double longitude;
	public Double points;
	public int going;
	public String dateCreated;
	public String dateModified;
	public int organizerID;
	public int categoryID;
	
	@Override
	public String toString() {
		return  "fecha: "
				+ date +" nombre: " + name;
				
				/*+ " hora: " + hora + " distancia: " + distancia
				+ " partidaLLegada: " + partidaLLegada + " lugar: " + lugar
				+ " valor: " + valor + " kit: " + kit
				+ " lugarInscripcion: " + lugarInscripcion + " puntuacion: " + puntuacion
				+ " descripcion: " + descripcion + " premios: " + premios+ " estado: " + estado
				+ " imagen: " + imagen+ " idOrganizador: " + idOrganizador; */
	}
	
	public Race() {
		super();

	}
	public Race(Parcel in){
		readFromParcel(in);
	}
	
	
	
	
	public Race(int raceID, String name, String distance, String location,
			String country, String countryCode, Date date, Date startTime,
			String cost, String kit, String registration, String awards,
			String description, int benefit, String status, String sponsors,
			Double latitude, Double longitude, Double points, int going,
			String dateCreated, String dateModified, int organizerID,
			int categoryID) {
		super();
		this.raceID = raceID;
		this.name = name;
		this.distance = distance;
		this.location = location;
		this.country = country;
		this.countryCode = countryCode;
		this.date = date;
		this.startTime = startTime;
		this.cost = cost;
		this.kit = kit;
		this.registration = registration;
		this.awards = awards;
		this.description = description;
		this.benefit = benefit;
		this.status = status;
		this.sponsors = sponsors;
		this.latitude = latitude;
		this.longitude = longitude;
		this.points = points;
		this.going = going;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.organizerID = organizerID;
		this.categoryID = categoryID;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(raceID);
		dest.writeString(name);
		dest.writeString(distance);
		dest.writeString(location);
		dest.writeString(country);
		dest.writeString(countryCode);
		dest.writeSerializable(date);
		dest.writeSerializable(startTime);
		dest.writeString(cost);
		dest.writeString(kit);
		dest.writeString(registration);
		dest.writeString(awards);
		dest.writeString(description);
		dest.writeInt(benefit);
		dest.writeString(status);
		dest.writeString(sponsors);
		dest.writeDouble(latitude);
		dest.writeDouble(longitude);
		dest.writeDouble(points);
		dest.writeInt(going);
		dest.writeString(dateCreated);
		dest.writeString(dateModified);
		dest.writeInt(organizerID);
		dest.writeInt(categoryID);
		
	}
	private void readFromParcel(Parcel in){
		raceID = in.readInt();
		name = in.readString();
		distance = in.readString();
		location = in.readString();
		country = in.readString();
		countryCode = in.readString();
		date = (java.util.Date) in.readSerializable();
		startTime = (java.util.Date) in.readSerializable();
		cost= in.readString();
		kit = in.readString();
		registration = in.readString();
		awards = in.readString();
		description = in.readString();
		benefit = in.readInt();
		status = in.readString();
	    sponsors = in.readString();
		latitude = in.readDouble();
		longitude = in.readDouble();
		points = in.readDouble();
		going = in.readInt();
		dateCreated = in.readString();
		dateModified = in.readString();
		organizerID = in.readInt();
		categoryID = in.readInt();
	}
	public static final Parcelable.Creator<Race> CREATOR = new Parcelable.Creator<Race>() {
		 @Override
		public Race createFromParcel(Parcel in) {
			return new Race(in);
		}
		 @Override
		public Race[] newArray(int size) {
			return new Race[size];
		}
	};

	@Override
	public boolean isSection() {
		// TODO Auto-generated method stub
		return false;
	}


}
