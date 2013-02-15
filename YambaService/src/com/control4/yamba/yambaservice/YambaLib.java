package com.control4.yamba.yambaservice;

public class YambaLib {

	static {
		System.loadLibrary("yamba");
	}
	public static native void log(String message);
}
