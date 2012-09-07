package com.fapiko.faceworm.mobile.android;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import org.zeromq.ZMQ;

import java.text.BreakIterator;

public class FacewormMobileAndroid extends Activity {

	public final String TAG = "FacewormMobileAndroid";

	private ImageButton playPauseButton;
	private ImageButton nextTrackButton;
	private ImageButton thumbsUpButton;
	private ImageButton thumbsDownButton;
	private ZMQ.Socket socket;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.v(TAG, "onCreate method");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ZMQ.Context context = ZMQ.context(1);
		socket = context.socket(ZMQ.PAIR);
		socket.connect("tcp://172.16.1.6:5555");

		addButtonListeners();

	}

	protected ZMQ.Socket getSocket() {
		return socket;
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

			Log.v(TAG, "Button pressed: " + buttonId);

			StringBuffer message = new StringBuffer("ACTION|");

			switch(buttonId) {
				case PLAYPAUSE:
					message.append("PLAY_PAUSE");
					break;

				case NEXTTRACK:
					message.append("NEXT_TRACK");
					break;

				case THUMBSDOWN:
					message.append("THUMBS_DOWN");
					break;

				case THUMBSUP:
					message.append("THUMBS_UP");
					break;

				default:
					Log.w(TAG, "Unknown buttonId pressed: " + buttonId);

			}

			parent.getSocket().send(String.valueOf(message).getBytes(), 0);

		}
	}

}
