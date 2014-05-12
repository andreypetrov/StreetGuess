package com.petrovdevelopment.streetguess.model;

import com.google.gson.Gson;

/**
 * A model representing a location, with immutable properties.
 * 
 * @author Andrey
 * 
 */
public class Location {
	public static final String LOCATION_EXTRA = "location extra";

	// public static final String LATITUDE_EXTRA = "latitude extra";
	// public static final String LONGITUDE_EXTRA = "longitude extra";
	// public static final String NAME_EXTRA = "name extra";
	// public static final String IMAGE_URL_EXTRA = "image url extra";

	public static final Double DEFAULT_LATITUDE = -33.867d;
	public static final Double DEFAULT_LONGITUDE = 151.206d;

	public final Double latitude;
	public final Double longitude;
	public final String name;
	public final String imageUrl;
	public final String description;

	public Location() {
		imageUrl = "http://1.bp.blogspot.com/-C9sdBW15K8s/T9IfvrVJ_pI/AAAAAAAACcs/xs44YbBF1eI/s1600/ao1.png";
		name = "test dummy name";
		latitude = DEFAULT_LATITUDE;
		longitude = DEFAULT_LONGITUDE;
		description = "";
	}

	public Location(Double latitude, Double longitude, String name, String imageUrl, String description) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
	}

	public String getJson() {
		return new Gson().toJson(this);
	}
}
