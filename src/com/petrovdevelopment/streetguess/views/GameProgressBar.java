package com.petrovdevelopment.streetguess.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.petrovdevelopment.streetguess.R;
import com.petrovdevelopment.streetguess.util.U;
import com.petrovdevelopment.streetguess.util.ViewAttrsUtil;

public class GameProgressBar extends LinearLayout {
	public static final int DEFAULT_MAX = 10;
	public static final int DEFAULT_STEP_GAP_IN_PIXELS = 30;
	public static final int DEFAULT_STEP_WIDTH_IN_PIXELS = 30;
	public static final int DEFAULT_STEP_HEIGHT_IN_PIXELS = 30;
	int max;
	int value; // 0 based
	int stepLayoutId;
	int stepGap;
	int stepWidth;
	int stepHeight;

	public GameProgressBar(Context context) {
		super(context);
		init();
	}

	public GameProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		ViewAttrsUtil viewAttrsUtil = new ViewAttrsUtil(context, attrs, R.styleable.GameProgressBar);
		max = viewAttrsUtil.getIntFromXml(R.styleable.GameProgressBar_max, DEFAULT_MAX);
		int progressValue = viewAttrsUtil.getIntFromXml(R.styleable.GameProgressBar_value, 0);
		stepLayoutId = viewAttrsUtil.getResourceIdFromXml(R.styleable.GameProgressBar_step_layout, -1);
		stepGap = viewAttrsUtil.getDimensionPixelSizeFromXml(R.styleable.GameProgressBar_step_gap, DEFAULT_STEP_GAP_IN_PIXELS);
		stepWidth = viewAttrsUtil.getDimensionPixelSizeFromXml(R.styleable.GameProgressBar_step_width, DEFAULT_STEP_GAP_IN_PIXELS);
		stepHeight = viewAttrsUtil.getDimensionPixelSizeFromXml(R.styleable.GameProgressBar_step_height, DEFAULT_STEP_GAP_IN_PIXELS);

		U.log(this, "step gap: " + stepGap);
		setProgress(progressValue);
		init();
	}

	private void init() {

	}

	public void incrementProgress() {
		if (getValue() >= getMax()) throw new IllegalStateException("Cannot increment progress above or equal to the bar size which is " + max);
		U.log(this, "increment");
		// TODO add animation here to slowly increaze the size of the view from 0 to max
		addView(createStepView());
		value++;
	}

	public void decrementProgress() {
		if (getValue() == 0) throw new IllegalStateException("Cannot decrement progress below 0");
		value--;
		removeViewAt(value);
	}

	/**
	 * Set progress from 0 to getSize()-1. Triggers corresponding animation, if any
	 * 
	 * @param progress
	 */
	private void setProgress(int progress) {
		U.log(this, "progress: " + progress);
		if (progress >= getMax()) throw new IllegalStateException("Cannot set a progress above or equal to the bar size, which is " + max);
		if (progress < 0) throw new IllegalStateException("Cannot set a progress below 0");
		if (progress > getValue()) {
			for (int i = getValue(); i < progress; i++)
				incrementProgress();
		} else {
			for (int i = getValue(); i > progress; i--)
				decrementProgress();
		}

	}

	public boolean hasCompleted() {
		return getValue() >= getMax();
	}

	public int getValue() {
		return value;
	}

	public int getMax() {
		return max;
	}

	private View createStepView() {
		View v = inflate(getContext(), stepLayoutId, null);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(stepWidth, stepHeight);
		if (getChildCount() > 0) {// if this is not the first view, then add margin
			if (getOrientation() == HORIZONTAL) layoutParams.setMargins(stepGap, 0, 0, 0);
			else layoutParams.setMargins(0, stepGap, 0, 0);
		}
		v.setLayoutParams(layoutParams);
		return v;
	}
}
