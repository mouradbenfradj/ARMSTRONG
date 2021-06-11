package pfe.mbf.armstrong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class ArmStrong extends Activity {

	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_armstrong);
		VideoView video;
        video = (VideoView)findViewById(R.id.videoView1);
        video.setVideoPath("sdcard/video.mp4");
        video.start();
		btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.MENUP"));
			}
		});
	}

}
