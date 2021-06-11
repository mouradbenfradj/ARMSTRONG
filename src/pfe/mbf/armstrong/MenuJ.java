package pfe.mbf.armstrong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuJ extends Activity {

	ImageButton jouer,instruction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menuj);
		

		jouer = (ImageButton)findViewById(R.id.continuer);
		jouer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
		        Intent secondeActivite = new Intent(MenuJ.this, Jeux.class);
		         
		        secondeActivite.putExtra("choix", true);
		 
		        startActivity(secondeActivite);
			}
		});
		instruction = (ImageButton)findViewById(R.id.nouveu);
		instruction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			       Intent secondeActivite = new Intent(MenuJ.this, Jeux.class);
			         
			        secondeActivite.putExtra("choix", false);
			 
			        startActivity(secondeActivite);
			
			}
		});
	}

}
