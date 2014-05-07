package com.petrovdevelopment.streetguess;

import android.os.Bundle;
import android.widget.ListView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * The main guess activity, in which the user selects a location on the map, and confirms by pressing the "Make guess" button
 * 
 * @author Andrey
 * 
 */
public class GuessActivity extends RoboActivity {

	// @InjectView(R.id.mainMenu) ListView mMainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);

	}
}
