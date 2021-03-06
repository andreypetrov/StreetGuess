package com.petrovdevelopment.streetguess.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class GenericAdapter<Data> extends BaseAdapter {
	List<Data> data;
	Context context;

	public GenericAdapter(List<Data> data, Context context) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Data getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(getCellResourceId(), parent, false);
			convertView.setTag(createHolder(convertView));
		}
		update(convertView, position);
		return convertView;
	}

	public abstract void update(View view, int position);

	public abstract int getCellResourceId();

	/**
	 * To be used only in views with more than ten cells, to improve performance. 
	 * The object returned by getHolder can be used within the update method with view.getTag()
	 * 
	 * @param view
	 * @return
	 */
	public abstract Object createHolder(View view);

}
