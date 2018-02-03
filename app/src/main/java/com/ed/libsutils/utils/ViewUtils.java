package com.ed.libsutils.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {
	public static void setSize(View view, int width, int height) {
		ViewGroup.LayoutParams params = view.getLayoutParams();
		params.width = width;
		params.height = height;
		view.setLayoutParams(params);
		view.requestLayout();
		view.invalidate();
	}

	public static void setSize(View targetView, View sourceView) {
		setSize(targetView, sourceView.getWidth(), sourceView.getHeight());
	}

	public static void setHeight(View view, Activity activity) {
		view.getLayoutParams().height = activity.getWindow().getDecorView().getHeight();
	}

	public static int[] getLocationOnScreen(View view) {
		int location[] = new int[2];
		view.getLocationOnScreen(location);
		return location;
	}
}
