package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.petrovdevelopment.streetguess.model.Round;

public class StreetViewActivity extends RoboActivity {
	private Round mRound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mRound = createRound(); //TODO replace this with getting the round from the game model
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street_view);
	}

	public Round createRound() {
		return new Round();
	}

	public void onMakeGuessClick(View v) {
		Intent intent = new Intent(this, GuessActivity.class);
		intent.putExtra(Round.ROUND_EXTRA, mRound.getJson());
		startActivity(intent);
	}
}
