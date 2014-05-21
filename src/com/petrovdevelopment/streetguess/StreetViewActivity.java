package com.petrovdevelopment.streetguess;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.petrovdevelopment.streetguess.model.Round;
import com.petrovdevelopment.streetguess.util.U;
import com.petrovdevelopment.streetguess.views.GameProgressBar;

public class StreetViewActivity extends RoboActivity {
	private Round mRound;

	@InjectView(R.id.progress) GameProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mRound = createRound(); // TODO replace this with getting the round from the game model
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street_view);
		updateUi(mRound);
	}

	private void updateUi(Round mRound2) {
		mProgressBar.incrementProgress();
		// ObjectAnimator.ofFloat(mProgressBar, "scaleX", 0f, 1f).setDuration(3000).start();
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
