package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.petrovdevelopment.streetguess.model.Location;
import com.petrovdevelopment.streetguess.model.Round;
import com.petrovdevelopment.streetguess.util.MapUtil;
import com.petrovdevelopment.streetguess.util.U;

/**
 * The main guess activity, in which the user selects a location on the map, and confirms by pressing the "Make guess" button
 * 
 * @author Andrey
 * 
 */
public class GuessActivity extends RoboFragmentActivity {
	private Round mRound; // the actual location of the correct answer
	private GoogleMap mMap;
	private Marker mCurrentMarker; // the currently selected guess locations

	private SupportMapFragment mMapFragment;
	private static final int MAP_ZOOM = 13;

	@InjectView(R.id.makeGuess) private View mMakeGuessButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String roundJson = getIntent().getStringExtra(Round.ROUND_EXTRA);
		mRound = new Gson().fromJson(roundJson, Round.class);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);
		initMapIfPresent();
		updateUi();
	}

	public void initMapIfPresent() {
		mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		if (mMapFragment != null) {
			mMap = mMapFragment.getMap();
			if (mMap != null) initMap();
			else showErrorMessage(R.string.map_cannot_load);
		} else showErrorMessage(R.string.map_cannot_load);
	}

	public void initMap() {
		addMarker(mMap, mRound.correctLocation);
		mMap.setOnMapClickListener(new OnMapClickListener() {

			/**
			 * Remove the old marker and add a new one
			 */
			@Override
			public void onMapClick(LatLng point) {
				if (mCurrentMarker != null) mCurrentMarker.remove();
				mCurrentMarker = mMap.addMarker(createGuessMarkerOptions(point));
				updateUi();
			}

		});
	}

	public MarkerOptions createGuessMarkerOptions(LatLng point) {
		BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.questionmark);
		String title = getString(R.string.guess);
		return new MarkerOptions().title(title).position(point).icon(bitmapDescriptor);
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
		map.addMarker(new MarkerOptions().title(location.name).snippet(location.description).position(latLng));
		// map.getUiSettings().setScrollGesturesEnabled(false);
	}

	public void onMakeGuessClick(View v) {
		U.log(this, mRound.correctLocation.name);
		updateModel();
		mMap.addMarker(mRound.correctLocation.createMarker(this));
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mRound.correctLocation.latLng, 1));
		addLineBetweenGuessAndCorrectLocation();

	}

	/**
	 * TODO
	 */
	private void updateModel() {
		long distanceInMeters = Math.round(MapUtil.distance(mRound.correctLocation.latLng, mCurrentMarker.getPosition()));

		U.log(this, distanceInMeters / 1000);
		// based on distance, calculate points

	}

	private void addLineBetweenGuessAndCorrectLocation() {
		// TODO make line color variate based on distance between coordinates
		mMap.addPolyline(new PolylineOptions().add(mCurrentMarker.getPosition(), mRound.correctLocation.latLng).width(5).color(Color.RED));

	}

	private void updateUi() {
		if (mCurrentMarker != null) enableGuessButton();
		else disableGuessButton();
	}

	private void enableGuessButton() {
		mMakeGuessButton.setEnabled(true);
	}

	private void disableGuessButton() {
		mMakeGuessButton.setEnabled(false);
	}
}
