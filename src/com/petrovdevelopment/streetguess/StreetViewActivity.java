package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.petrovdevelopment.streetguess.model.Round;
import com.petrovdevelopment.streetguess.util.MathUtil;
import com.petrovdevelopment.streetguess.util.U;
import com.petrovdevelopment.streetguess.views.GameProgressBar;

public class StreetViewActivity extends RoboActivity {
	public static final int ANIMATION_TIME_IN_MILLIS = 3000;
	private Round mRound;

	@InjectView(R.id.progress)
	GameProgressBar mProgressBar;
	StreetViewPanoramaFragment mStreetViewFragment;
	@InjectView(R.id.container)
	ViewGroup container;

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

		for (int i = 0; i < 10; i++) {
			U.log(this, "init street view");
			StreetViewPanoramaView streetViewPanoramaView = new StreetViewPanoramaView(this, new StreetViewPanoramaOptions().position(MathUtil
					.generateRandomLatLng()));
			container.addView(streetViewPanoramaView);
			
//			
//			final StreetViewPanorama panorama = mStreetViewFragment.getStreetViewPanorama();
//			U.log(this, panorama);
//			if (panorama != null) {
//				OnStreetViewPanoramaChangeListener listener = new OnStreetViewPanoramaChangeListener() {
//
//					@Override
//					public void onStreetViewPanoramaChange(StreetViewPanoramaLocation location) {
//						if (location != null) {
//							// do nothing
//							U.log(this, location.panoId);
//						} else {
//							U.log(this, "location is null");
//							initStreetView(new Round());
//						}
//					}
//				};
//
//				panorama.setPosition(round.correctLocation.latLng);
//				panorama.getLocation();
//				panorama.setOnStreetViewPanoramaChangeListener(listener);
//				panorama.setOnStreetViewPanoramaCameraChangeListener(new OnStreetViewPanoramaCameraChangeListener() {
//
//					@Override
//					public void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera camera) {
//						U.log(this, "camera has changed");
//					}
//				});
//			} else {
//				U.log(this, "panorama is null");
//			}
		}
	}

}
