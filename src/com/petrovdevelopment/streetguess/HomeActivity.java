package com.petrovdevelopment.streetguess;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.petrovdevelopment.streetguess.adapters.MainMenuAdapter;

public class HomeActivity extends RoboActivity {

	@InjectView(R.id.mainMenu) ListView mMainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initMainMenu();
	}

	public void initMainMenu() {
		List<String> menuOptions = getMenuOptions();
		mMainMenu.setAdapter(getMainMenuAdapter(menuOptions));
	}

	private List<String> getMenuOptions() {
		List<String> menuOptions = new ArrayList<String>();
		menuOptions.add("Guess");
		menuOptions.add("Marathon");
		menuOptions.add("Competition");
		menuOptions.add("yay");
		return menuOptions;
	}

	private ListAdapter getMainMenuAdapter(List<String> menuOptions) {
		return new MainMenuAdapter(menuOptions, this);
	}
}
