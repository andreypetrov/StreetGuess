package com.petrovdevelopment.streetguess.model;

import com.google.gson.Gson;

public class Location {
	public static final String LOCATION_EXTRA = "location extra";
	public static final String LATITUDE_EXTRA = "latitude extra";
	public static final String LONGITUDE_EXTRA = "longitude extra";
	public static final Double DEFAULT_LATITUDE = -33.867d;
	public static final Double DEFAULT_LONGITUDE = 151.206d;

	public Double latitude;
	public Double longitude;
	public String name;
	public String imageUrl;

	public Location() {
		name = "test dummy name";
	}

	public String getJson() {
		return new Gson().toJson(this);
	}
}
