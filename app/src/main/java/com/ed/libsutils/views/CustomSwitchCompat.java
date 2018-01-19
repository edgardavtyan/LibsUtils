package com.ed.libsutils.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.ed.libsutils.utils.CustomColor;

public class CustomSwitchCompat extends SwitchCompat {
	private static final int DEFAULT_TRACK_COLOR = Color.parseColor("#a6a6a6");
	private static final int DEFAULT_THUMB_COLOR = Color.parseColor("#ececec");

	public CustomSwitchCompat(Context context) {
		super(context);
	}

	public CustomSwitchCompat(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setTint(int color) {
		CustomColor primaryColor = new CustomColor(color);
		ColorStateList thumbStates = new ColorStateList(
				new int[][]{new int[]{android.R.attr.state_checked},
							new int[]{-android.R.attr.state_checked}},
				new int[]{color, DEFAULT_THUMB_COLOR});
		ColorStateList trackStates = new ColorStateList(
				new int[][]{new int[]{android.R.attr.state_checked},
							new int[]{-android.R.attr.state_checked}},
				new int[]{primaryColor.fade(56), DEFAULT_TRACK_COLOR});

		DrawableCompat.setTintList(getThumbDrawable(), thumbStates);
		DrawableCompat.setTintList(getTrackDrawable(), trackStates);
	}
}
