package org.techtown.callstatus;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;

public class SimpleWindow extends StandOutWindow {

	private static MediaRecorder recorder = null;

	@Override
	public String getAppName() {
		return "플로팅알림";
	}

	@Override
	public int getAppIcon() {
		return android.R.drawable.ic_menu_close_clear_cancel;
	}

	@Override
	public void createAndAttachView(int id, FrameLayout frame) {
		// create a new layout from body.xml
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.simple, frame, true);
		
		LinearLayout container = (LinearLayout) frame.findViewById(R.id.container);
		Drawable background = container.getBackground();
		background.setAlpha(97);
	}

	// the window will be centered
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		float window_width = getResources().getDimension(R.dimen.window_width);
		float window_height = getResources().getDimension(R.dimen.window_height);
		float window_margin_right = getResources().getDimension(R.dimen.window_margin_right);
		float window_margin_top = getResources().getDimension(R.dimen.window_margin_top);

		int widthInt = Float.valueOf(window_width).intValue();
		int heightInt = Float.valueOf(window_height).intValue();
		int marginRightInt = Float.valueOf(window_margin_right).intValue();
		int marginTopInt = Float.valueOf(window_margin_top).intValue();
		
		StandOutLayoutParams params = new StandOutLayoutParams(id, widthInt, heightInt,
											StandOutLayoutParams.RIGHT, StandOutLayoutParams.TOP);
		
		params.x = params.x - marginRightInt;
		params.y = params.y + marginTopInt;
		
		return params;
	}

	// move the window by dragging the view
	@Override
	public int getFlags(int id) {
		return super.getFlags(id) | StandOutFlags.FLAG_BODY_MOVE_ENABLE
				| StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
	}

	@Override
	public String getPersistentNotificationMessage(int id) {
		return "알림 메시지입니다.";
	}

	@Override
	public Intent getPersistentNotificationIntent(int id) {
		return StandOutWindow.getCloseIntent(this, SimpleWindow.class, id);
	}

	@Override
	public void onReceiveData(int id, int requestCode, Bundle bundle, Class<? extends StandOutWindow> fromCls, int fromId) {
		Log.d("SimpleWindow", "onReceiveData() 호출됨.");
		
		if (requestCode == 1001) {
			Window window = getWindow(id);

		} else if(requestCode == 1002) {

			Window window = getWindow(id);

			Button close = (Button) window.findViewById(R.id.close);
			close.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					StandOutWindow.closeAll(SimpleWindow.this, SimpleWindow.class);
				}
			});

		}

		
	}

}
