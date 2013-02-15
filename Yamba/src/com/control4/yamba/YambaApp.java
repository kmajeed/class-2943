package com.control4.yamba;

import com.control4.yamba.yambalib.YambaManager;

import android.app.Application;

public class YambaApp extends Application {
	protected YambaManager yambaManager;

	@Override
	public void onCreate() {
		super.onCreate();
		yambaManager = new YambaManager(this);
	}
}
