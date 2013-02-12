package com.control4.yamba;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

		Log.d("Yamba", "onClicked with text: " + status);
	}

	// --- TextWatcher Callbacks ---
	@Override
	public void afterTextChanged(Editable s) {
		int count = 140 - s.length();
		textCount.setText(String.valueOf(count));
	
		if(count<50) {
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
