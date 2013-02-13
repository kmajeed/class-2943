package com.control4.yamba;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class StatusProvider extends ContentProvider {
	private static final String TAG = "StatusProvider";
	private DbHelper dbHelper;

	@Override
	public boolean onCreate() {
		dbHelper = new DbHelper(getContext());
		return (dbHelper == null) ? false : true;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insertWithOnConflict(StatusContract.TABLE, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);

		if (id == -1) {
			return null;
		}

		Uri ret = ContentUris.withAppendedId(uri,
				values.getAsLong(StatusContract.Columns.ID));
		Log.d(TAG, "insert uri: " + ret);
		return ret;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	// delete from status where id=?
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int recs = db.delete(StatusContract.TABLE, selection, selectionArgs);
		Log.d(TAG, "deleted recs: "+recs);
		return recs;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
