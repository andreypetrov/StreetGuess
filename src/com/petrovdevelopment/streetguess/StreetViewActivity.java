package com.petrovdevelopment.streetguess;

import com.petrovdevelopment.streetguess.model.Location;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StreetViewActivity extends RoboActivity {
	private Location mLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mLocation = createLocation();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street_view);
	}

	public Location createLocation() {
		return new Location();
	}

	public void onMakeGuessClick(View v) {
		Intent intent = new Intent(this, GuessActivity.class);
		intent.putExtra(Location.LOCATION_EXTRA, mLocation.getJson());
		startActivity(intent);
	}
}
