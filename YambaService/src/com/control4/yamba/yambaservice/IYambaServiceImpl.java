package com.control4.yamba.yambaservice;

import java.util.ArrayList;
import java.util.List;

import android.os.RemoteException;
import android.util.Log;

import com.control4.yamba.yambalib.IYambaService;
import com.control4.yamba.yambalib.YambaStatus;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
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

	@Override
	public List<YambaStatus> getTimeline(int records) throws RemoteException {
		List<YambaStatus> ret = new ArrayList<YambaStatus>();
		try {
			YambaClient yamba = new YambaClient("student", "password");
			List<Status> timeline = yamba.getTimeline(records);
			for (Status status : timeline) {
				ret.add(new YambaStatus((int) status.getId(), status.getUser(),
						status.getMessage(), status.getCreatedAt().getTime()));
			}
		} catch (YambaClientException e) {
			Log.e(TAG, "Failed to post", e);
			e.printStackTrace();
		}
		
		Log.d(TAG, "got records:"+ret.size());

		return ret;
	}

}
