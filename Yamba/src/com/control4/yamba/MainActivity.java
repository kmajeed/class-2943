package com.control4.yamba;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Fragment statusFragment = getFragmentManager().findFragmentById(
				R.id.fragment_status);

		if (statusFragment != null && statusFragment.isAdded())
			menu.findItem(R.id.item_status).setVisible(false);
		else
			menu.findItem(R.id.item_status).setVisible(true);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_status:
			startActivity(new Intent(this, StatusActivity.class));
			return true;
		case R.id.item_refresh:
			startService(new Intent(this, RefreshService.class));
			return true;
		case R.id.item_purge:
			getContentResolver().delete(StatusContract.CONTENT_URI, null, null);
			return true;
		default:
			return false;
		}
	}

}
