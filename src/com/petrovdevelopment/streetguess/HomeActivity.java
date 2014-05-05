package com.petrovdevelopment.streetguess;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.petrovdevelopment.streetguess.adapters.MainMenuAdapter;

public class HomeActivity extends FragmentActivity {

	@InjectView(R.id.mainMenu) ListView mMainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

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
		return menuOptions;
	}

	private ListAdapter getMainMenuAdapter(List<String> menuOptions) {
		return new MainMenuAdapter(menuOptions, this);
	}
}
