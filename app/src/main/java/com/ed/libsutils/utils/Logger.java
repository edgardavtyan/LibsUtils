package com.ed.libsutils.utils;

import android.util.Log;

import java.util.Locale;

public class Logger {
	@SuppressWarnings("unused")
	public static void d(Object caller, String message, Object... params) {
		Log.d(caller.getClass().getSimpleName(), String.format(Locale.getDefault(), message, params));
	}
}
