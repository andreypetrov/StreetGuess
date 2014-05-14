package com.petrovdevelopment.streetguess.model;

import android.content.Context;
import android.graphics.Color;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.petrovdevelopment.streetguess.R;

public class Round {
	public static final String ROUND_EXTRA = "round extra";

	public int points;
	public long distance;
	public Location correctLocation;
	public LatLng guess;

	public Round() {
		points = -1;
		distance = -1;
		correctLocation = new Location();
		guess = null;
	}

	/**
	 * ViewModel method to create a polyline between the guess and the correct location
	 * 
	 * @return
	 */
	public PolylineOptions createPolylineOptions() {
		return new PolylineOptions().add(guess, correctLocation.latLng).width(5).color(Color.RED);
	}

	/**
	 * ViewModel method to create a marker for the guess
	 * 
	 * @param context
	 * @return
	 */
	public MarkerOptions createGuessMarkerOptions(Context context) {
		BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.questionmark);
		String title = context.getResources().getString(R.string.guess);
		return new MarkerOptions().title(title).position(guess).icon(bitmapDescriptor);
	}

	public String getJson() {
		return new Gson().toJson(this);
	}
}
