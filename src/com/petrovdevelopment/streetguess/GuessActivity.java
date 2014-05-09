package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.petrovdevelopment.streetguess.model.Location;
import com.petrovdevelopment.streetguess.util.U;

/**
 * The main guess activity, in which the user selects a location on the map, and confirms by pressing the "Make guess" button
 * 
 * @author Andrey
 * 
 */
public class GuessActivity extends RoboActivity {
	private Location mLocation;

	// @InjectView(R.id.mainMenu) ListView mMainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String locationJson = getIntent().getStringExtra(Location.LOCATION_EXTRA);
		mLocation = new Gson().fromJson(locationJson, Location.class);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);

	}

	public void onMakeGuessClick(View v) {
		U.log(this, mLocation.name);
	}
}
