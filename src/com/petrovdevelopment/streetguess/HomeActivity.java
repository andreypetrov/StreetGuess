package com.petrovdevelopment.streetguess;

import java.util.Arrays;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.petrovdevelopment.streetguess.adapters.MainMenuAdapter;
import com.petrovdevelopment.streetguess.util.U;

public class HomeActivity extends RoboActivity {

	@InjectView(R.id.mainMenu) ListView mMainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// getActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initMainMenu();
	}

	public void initMainMenu() {
		List<String> menuOptions = getMenuOptions();
		mMainMenu.setAdapter(getMainMenuAdapter(menuOptions));
	}

	private List<String> getMenuOptions() {
		return Arrays.asList(getResources().getStringArray(R.array.main_menu));
	}

	private ListAdapter getMainMenuAdapter(List<String> menuOptions) {
		return new MainMenuAdapter(menuOptions, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item); //ensures fragments can also handle the call
		}
	}

	private void openSettings() {
		// TODO Auto-generated method stub
		U.log(this, "OpenSettings");
	}
}
