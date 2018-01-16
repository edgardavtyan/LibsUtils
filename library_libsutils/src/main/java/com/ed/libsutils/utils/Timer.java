package com.ed.libsutils.utils;

import android.os.Handler;

public class Timer {
	private final Handler handler;
	private final Runnable innerRunnable;

	public Timer(final int interval, final Runnable task) {
		this.handler = new Handler();
		this.innerRunnable = new Runnable() {
			@Override
			public void run() {
				task.run();
				handler.postDelayed(this, interval);
			}
		};
	}

	public void run() {
		innerRunnable.run();
	}

	public void stop() {
		handler.removeCallbacks(innerRunnable);
	}
}
