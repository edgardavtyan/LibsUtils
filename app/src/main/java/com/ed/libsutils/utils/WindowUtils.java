package com.ed.libsutils.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public final class WindowUtils {
    private WindowUtils() {
    }

    public static void makeStatusBarTransparent(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static boolean isPortrait(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void makeStatusBarSemiTransparent(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public static void toggleSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void closeSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static int getStatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return activity.getWindow().getStatusBarColor();
        } else {
            return -1;
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        Window window = activity.getWindow();
        View contentView = activity.findViewById(android.R.id.content);
        return window.getDecorView().getHeight() - contentView.getHeight();
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

	public static void addSystemUiFlag(Activity activity, int flag) {
		int originalFlags = activity.getWindow().getDecorView().getSystemUiVisibility();
		activity.getWindow().getDecorView().setSystemUiVisibility(originalFlags | flag);
	}

	public static void removeSystemUiFlag(Activity activity, int flag) {
		int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
		flags &= ~flag;
		activity.getWindow().getDecorView().setSystemUiVisibility(flags);
	}
}
