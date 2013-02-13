package com.control4.yamba;

import java.util.List;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class RefreshService extends IntentService {
	private static final String TAG = "RefreshService";

	public RefreshService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate");
	}

	/** Executes on the worker thread. */
	@Override
	public void onHandleIntent(Intent intent) {
		Log.d(TAG, "onStarted");

		DbHelper dbHelper = new DbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		try {
			YambaClient yamba = new YambaClient("student", "password");
			List<Status> timeline = yamba.getTimeline(20);
			for (Status status : timeline) {

				values.clear();
				values.put(StatusContract.Columns.ID, status.getId());
				values.put(StatusContract.Columns.USER, status.getUser());
				values.put(StatusContract.Columns.MESSAGE, status.getMessage());
				values.put(StatusContract.Columns.CREATED_AT, status
						.getCreatedAt().getTime());

				db.insertWithOnConflict(StatusContract.TABLE, null, values,
						SQLiteDatabase.CONFLICT_IGNORE);

				Log.d(TAG,
						String.format("%s: %s", status.getUser(),
								status.getMessage()));
			}
		} catch (YambaClientException e) {
			Log.e(TAG, "Failed to get the timeline", e);
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroyed");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
