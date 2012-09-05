package com.fapiko.faceworm.mobile.android;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FacewormMobileAndroid extends Activity {

	public final String TAG = "FacewormMobileAndroid";

	private ImageButton playPauseButton;
	private ImageButton nextTrackButton;
	private ImageButton thumbsUpButton;
	private ImageButton thumbsDownButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.v(TAG, "onCreate method");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		addButtonListeners();

	}

	public void addButtonListeners() {


		playPauseButton = (ImageButton) this.findViewById(R.id.playPauseButton);
		playPauseButton.setOnClickListener(new MyOnClickListener(this, MyOnClickListener.PLAYPAUSE));

		nextTrackButton = (ImageButton) this.findViewById(R.id.nextTrackButton);
		nextTrackButton.setOnClickListener(new MyOnClickListener(this, MyOnClickListener.NEXTTRACK));

		thumbsUpButton = (ImageButton) this.findViewById(R.id.thumbsUpButton);
		thumbsUpButton.setOnClickListener(new MyOnClickListener(this, MyOnClickListener.THUMBSUP));

		thumbsDownButton = (ImageButton) this.findViewById(R.id.thumbsDownButton);
		thumbsDownButton.setOnClickListener(new MyOnClickListener(this, MyOnClickListener.THUMBSDOWN));

	}

	private class MyOnClickListener implements View.OnClickListener {

		private final static int PLAYPAUSE = 0;
		private final static int NEXTTRACK = 1;
		private final static int THUMBSUP = 2;
		private final static int THUMBSDOWN = 3;

		private int buttonId;

		private FacewormMobileAndroid parent;

		public MyOnClickListener(FacewormMobileAndroid parent, int buttonId) {

			this.parent = parent;
			this.buttonId = buttonId;

		}

		@Override
		public void onClick(View view) {

			Log.d(TAG, "Button pressed: " + buttonId);

		}
	}

}
