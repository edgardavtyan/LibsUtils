package com.ed.libsutils.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DpConverter {
	public static int dpToPixel(int dp) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		int px = (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	public static int pixelToDp(int px) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		int dp = (int) (px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
		return dp;
	}
}
