package com.petrovdevelopment.streetguess.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.petrovdevelopment.streetguess.R;

public class MainMenuAdapter extends GenericAdapter<String> {

	public MainMenuAdapter(List<String> data, Context context) {
		super(data, context);
	}

	@Override
	public void update(View view, int position) {
		TextView textView = (TextView) view.findViewById(R.id.text);
		textView.setText(getItem(position));
	}

	@Override
	public int getCellResourceId() {
		return R.layout.cell_main_menu;
	}

	@Override
	public Object getHolder(View view) {
		return null;
	}

}
