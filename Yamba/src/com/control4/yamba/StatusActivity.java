package com.control4.yamba;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class StatusActivity extends Activity implements TextWatcher {
	private static final int MAX_COUNT = 140;
	private EditText editStatus;
	private TextView textCount;
	private int defaultTextColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);

		textCount = (TextView) findViewById(R.id.text_count);
		textCount.setText(String.valueOf(MAX_COUNT));
		defaultTextColor = textCount.getTextColors().getDefaultColor();

		editStatus = (EditText) findViewById(R.id.edit_status);
		editStatus.addTextChangedListener(this);
	}

	public void onClick(View v) {
		String status = editStatus.getText().toString();
		new PostStatusTask().execute(status);
	}

	class PostStatusTask extends AsyncTask<String, Void, String> {

		/** Executes on a separate worker thread. */
		@Override
		protected String doInBackground(String... params) {
			try {
				YambaClient yamba = new YambaClient("student", "password");
				yamba.postStatus(params[0]);
				Log.d("Yamba", "onClicked with text: " + params[0]);
				return "Successfully posted";
			} catch (YambaClientException e) {
				Log.e("Yamba", "Failed to post", e);
				e.printStackTrace();
				return "Failed to post";
			}
		}

		/** Executes on UI thread after doInBackground() is done. */
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG)
					.show();
		}

	}

	// --- TextWatcher Callbacks ---
	@Override
	public void afterTextChanged(Editable s) {
		int count = 140 - s.length();
		textCount.setText(String.valueOf(count));

		if (count < 50) {
			textCount.setTextColor(Color.RED);
		} else {
			textCount.setTextColor(defaultTextColor);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
