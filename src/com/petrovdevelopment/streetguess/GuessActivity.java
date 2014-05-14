package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboFragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
	private Marker mCurrentMarker;

	private SupportMapFragment mMapFragment;
	private static final int MAP_ZOOM = 13;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String locationJson = getIntent().getStringExtra(Location.LOCATION_EXTRA);
		mLocation = new Gson().fromJson(locationJson, Location.class);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);
		initMapIfPresent();
	}

	public void initMapIfPresent() {
		mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		if (mMapFragment != null) {
			mMap = mMapFragment.getMap();
			if (mMap != null) {
				initMap();
				addMarker(mMap, mLocation);
			} else showErrorMessage(R.string.map_cannot_load);
		} else showErrorMessage(R.string.map_cannot_load);

	}

	public void initMap() {
		mMap.setOnMapClickListener(new OnMapClickListener() {

			/**
			 * Remove the old marker and add a new one
			 */
			@Override
			public void onMapClick(LatLng point) {
				mCurrentMarker.remove();
				mCurrentMarker = mMap.addMarker(new MarkerOptions().title(getString(R.string.guess)).position(point));
			}
		});
	}

	/**
	 * Show an error message, because the map could not be loaded
	 */
	private void showErrorMessage(int stringResourceId) {
		U.log(this, getString(stringResourceId));
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
