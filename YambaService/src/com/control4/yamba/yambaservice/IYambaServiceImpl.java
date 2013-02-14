package com.control4.yamba.yambaservice;

import android.os.RemoteException;
import android.util.Log;

import com.control4.yamba.yambalib.IYambaService;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class IYambaServiceImpl extends IYambaService.Stub {
	private static final String TAG = "YambaService";
	
	@Override
	public boolean updateStatus(String status) throws RemoteException {
		try {
			YambaClient yamba = new YambaClient("student", "password");
			yamba.postStatus(status);
			return true;
		} catch (YambaClientException e) {
			Log.e(TAG, "Failed to post", e);
			e.printStackTrace();
			return false;
		}
	}

}
