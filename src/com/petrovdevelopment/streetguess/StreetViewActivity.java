package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanorama.OnStreetViewPanoramaChangeListener;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.petrovdevelopment.streetguess.model.Location;
import com.petrovdevelopment.streetguess.model.Round;
import com.petrovdevelopment.streetguess.util.U;
import com.petrovdevelopment.streetguess.views.GameProgressBar;

public class StreetViewActivity extends RoboActivity {
	public static final int ANIMATION_TIME_IN_MILLIS = 3000;
	private Round mRound;

	@InjectView(R.id.progress) GameProgressBar mProgressBar;
	// StreetViewPanoramaFragment mStreetViewFragment;
	// StreetViewPanoramaFragment mStreetViewFragment2;

	@InjectView(R.id.container) ViewGroup mContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mRound = createRound();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street_view);
		// mStreetViewFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetView1);
		// mStreetViewFragment2 = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetView2);

		updateUi(mRound);

	}

	private void updateUi(Round mRound2) {
		mProgressBar.incrementProgress(true);

		addFragment();
		// addFragment2();
		// initStreetView(mStreetViewFragment, mRound);
		// initStreetView(mStreetViewFragment2, mRound);
	}

	public Round createRound() {
		return new Round();// TODO replace this with getting the round from the game model
	}

	public void onMakeGuessClick(View v) {
		Intent intent = new Intent(this, GuessActivity.class);
		intent.putExtra(Round.ROUND_EXTRA, mRound.getJson());
		startActivity(intent);

	}

	public void addFragment() {
		U.log(this, "try toronto");
		StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
		options.position(new LatLng(Location.TORONTO_LAT, Location.TORONTO_LONG));
		StreetViewPanoramaFragment fragment = StreetViewPanoramaFragment.newInstance(options);
		getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
	}

	public void addFragment2() {
		U.log(this, "try cidney");
		StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
		options.position(new LatLng(Location.DEFAULT_LATITUDE, Location.DEFAULT_LONGITUDE));
		StreetViewPanoramaFragment fragment = StreetViewPanoramaFragment.newInstance(options);
		getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
	}

	// public void initStreetView(StreetViewPanoramaFragment streetViewPanoramaFragment, Round round) {
	// U.log(this, "init street view");
	// final StreetViewPanorama panorama = streetViewPanoramaFragment.getStreetViewPanorama();
	// if (panorama != null) {
	// OnStreetViewPanoramaChangeListener listener = new OnStreetViewPanoramaChangeListener() {
	//
	// @Override
	// public void onStreetViewPanoramaChange(StreetViewPanoramaLocation location) {
	// if (location != null) {
	// // do nothing
	// U.log(this, location.panoId);
	// } else {
	// U.log(this, "location is null");
	// }
	// }
	// };
	// panorama.setOnStreetViewPanoramaChangeListener(listener);
	// panorama.setPosition(round.correctLocation.latLng, 5000);
	// panorama.getLocation();
	// U.log(this, panorama.getLocation());
	//
	// } else {
	// U.log(this, "panorama is null");
	// }
	// }

}
