package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboFragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.petrovdevelopment.streetguess.model.Location;
import com.petrovdevelopment.streetguess.util.U;

/**
 * The main guess activity, in which the user selects a location on the map, and confirms by pressing the "Make guess" button
 * 
 * @author Andrey
 * 
 */
public class GuessActivity extends RoboFragmentActivity {
	private Location mLocation;
	private GoogleMap mMap;
	private SupportMapFragment mMapFragment;
	private static final int MAP_ZOOM = 13;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String locationJson = getIntent().getStringExtra(Location.LOCATION_EXTRA);
		mLocation = new Gson().fromJson(locationJson, Location.class);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);
		initMap();
	}

	public void initMap() {
		mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mMap = mMapFragment.getMap();
		addMarker(mMap, mLocation);
	}

	public static void addMarker(GoogleMap map, Location location) {
		LatLng latLng = new LatLng(location.latitude, location.longitude);
		map.setMyLocationEnabled(false);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAP_ZOOM));
		map.addMarker(new MarkerOptions().title(location.name).snippet(location.description).position(latLng));
		map.getUiSettings().setScrollGesturesEnabled(false);
	}

	public void onMakeGuessClick(View v) {
		U.log(this, mLocation.name);
	}
}
