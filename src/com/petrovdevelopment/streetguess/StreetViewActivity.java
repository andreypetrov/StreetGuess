package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanorama.OnStreetViewPanoramaChangeListener;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.petrovdevelopment.streetguess.model.Round;
import com.petrovdevelopment.streetguess.util.U;
import com.petrovdevelopment.streetguess.views.GameProgressBar;

public class StreetViewActivity extends RoboActivity {
	public static final int ANIMATION_TIME_IN_MILLIS = 3000;
	private Round mRound;

	@InjectView(R.id.progress) GameProgressBar mProgressBar;
	StreetViewPanoramaFragment mStreetViewFragment;

	// @InjectView(R.id.image) ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mRound = createRound(); // TODO replace this with getting the round from the game model
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street_view);
		mStreetViewFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetView);
		updateUi(mRound);

	}

	private void updateUi(Round mRound2) {
		mProgressBar.incrementProgress(true);
		initStreetView(mRound);
		// Picasso.with(this).setDebugging(true);
		// Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(mImageView);
	}

	public Round createRound() {
		return new Round();
	}

	public void onMakeGuessClick(View v) {
		Intent intent = new Intent(this, GuessActivity.class);
		intent.putExtra(Round.ROUND_EXTRA, mRound.getJson());
		startActivity(intent);
	}

	public void initStreetView(Round round) {
		U.log(this, "init street view");
		final StreetViewPanorama panorama = mStreetViewFragment.getStreetViewPanorama();
		if (panorama != null) {
			OnStreetViewPanoramaChangeListener listener = new OnStreetViewPanoramaChangeListener() {

				@Override
				public void onStreetViewPanoramaChange(StreetViewPanoramaLocation location) {
					if (location != null) {
						// do nothing
						U.log(this, location.panoId);
					} else {
						U.log(this, "location is null");
						initStreetView(new Round());
					}
				}
			};

			panorama.setPosition(round.correctLocation.latLng, 50000);
			panorama.getLocation();
			panorama.setOnStreetViewPanoramaChangeListener(listener);
		} else {
			U.log(this, "panorama is null");
		}
	}
	
	

}
