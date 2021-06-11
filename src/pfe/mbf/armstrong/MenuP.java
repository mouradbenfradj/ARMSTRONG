package pfe.mbf.armstrong;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuP extends Activity {

	ImageButton jouer,instruction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menup);
		MediaPlayer menuM = MediaPlayer.create(this, R.raw.menu);
		menuM.start();
		
		jouer = (ImageButton)findViewById(R.id.jouer);
		jouer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.MENUJ"));
			}
		});
		instruction = (ImageButton)findViewById(R.id.instruction);
		instruction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent("android.intent.action.INSTRUCTION"));
			}
		});
	}

}
