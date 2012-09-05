package com.fapiko.faceworm.mobile.android;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FacewormMobileAndroid extends Activity {

	public final String TAG = "FacewormMobileAndroid";

	private Button playPauseButton;
	private Button nextTrackButton;
	private Button thumbsUpButton;
	private Button thumbsDownButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.v(TAG, "onCreate method");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		addButtonListeners();

	}

	public void addButtonListeners() {

		playPauseButton = (Button) findViewById(R.id.playPauseButton);
		playPauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "Play Pressed");
			}
		});

	}

}
