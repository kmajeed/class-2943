package com.control4.yamba;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class TimelineFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String[] FROM = { StatusContract.Columns.USER,
			StatusContract.Columns.MESSAGE };
	private static final int[] TO = { android.R.id.text1, android.R.id.text2 };
	private static final int LOADER_ID = 42;
	private SimpleCursorAdapter adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setEmptyText("Loading...");
		
		adapter = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_2, null, FROM, TO, 0);

		setListAdapter(adapter);

		getLoaderManager().initLoader(LOADER_ID, null, this);
	}

	// --- Loader Callbacks ---

	/** Executes on a non-UI thread. */
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		if(id!=LOADER_ID) return null;
		return new CursorLoader(getActivity(), StatusContract.CONTENT_URI,
				null, null, null, StatusContract.DEFAULT_SORT);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

		adapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
}