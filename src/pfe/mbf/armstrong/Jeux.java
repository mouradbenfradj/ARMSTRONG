package pfe.mbf.armstrong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Jeux extends Activity {

	public static final int VICTORY_DIALOG = 0;
	public static final int DEFEAT_DIALOG = 1;

	private Interface mView = null;
	private Detecteur mDetecteur = null;
	private Variable b;
	
	
	public DatabaseHandler selec;
	private Boolean choix;
	public int rapiditer,victoire,perte;
	public static MediaPlayer music,music1,music2;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeux);
		Log.d("jeux", "create");
		music = MediaPlayer.create(this, R.raw.jeux);

		mView = new Interface(this);
		setContentView(mView);
		
		mDetecteur = new Detecteur(this);
		
		b = new Variable();
		mView.setVariable(b);
		mDetecteur.setVariable(b);
		
		Intent i = getIntent();		
		choix = i.getBooleanExtra("choix", false);
		music1 = MediaPlayer.create(this, R.raw.fight);
		music2 =MediaPlayer.create(this, R.raw.combat);
		selec = new DatabaseHandler(Jeux.this);	
		selec.ouverture();
		if(choix){
			b.level=selec.getLevel();
			selec.setVictoire(0);
			selec.setPerte(0);
			selec.setManche(1);
			mView.force=selec.getForce(selec.getLevel());
			b.k=selec.getRapiditer(selec.getLevel());
			b.setRapiditer(rapiditer);
		}else{
			selec.res();
			b.level=selec.getLevel();
			mView.force=selec.getForce(selec.getLevel());
			b.setRapiditer(selec.getRapiditer(selec.getLevel()));
		}

		mView.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Log.d("jeux", "click");
				b.rotation=b.getForce()+2;
				b.setForce(b.getForce()+2);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("jeux", "resume");
		b.k=selec.getRapiditer(selec.getLevel());
		mDetecteur.resume();
		if(music.isPlaying()){music.release();}
		if(music1.isPlaying()){music1.stop();
		}
		if(music2.isPlaying()){music2.stop();}
		
	
		
	} 

	@Override
	protected void onPause() {
		super.onStop();
		Log.d("jeux", "pause");
		music.stop();;
		music1.stop();
		music2.stop();
		mDetecteur.pause();
		mView.pause();
	}
	
	
	@Override
	public Dialog onCreateDialog (int id) {
		Log.d("Essai",  "alert creation");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case VICTORY_DIALOG:
			builder.setCancelable(false)
			.setMessage("vous avez gagner")
			.setTitle(" monche")
			.setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(selec.getVict()!=2){
						selec.setVictoire(selec.getVict()+1);
						selec.setManche(selec.getManche()+1);
					}else{
						Log.d("Essai",  "gainclickfalse"+selec.getVict());
						selec.setVictoire(0);
						selec.setPerte(0);
						selec.setManche(1);
						mView.s=true;
						selec.updateLevel(selec.getLevel()+1);
					}
					selec.setManche(selec.getManche()+1);
					b.change=true;
					b.level=selec.getLevel();
					mView.force=selec.getForce(selec.getLevel());
					b.k=selec.getRapiditer(selec.getLevel());
					mDetecteur.reset();
					mDetecteur.resume();
				}
			});
			
			break;

		case DEFEAT_DIALOG:
			builder.setCancelable(false)
			.setMessage("vous avez perdu"+selec.getManche())
			.setTitle("monche")
			.setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(selec.getPert()!=2){
						selec.setManche((selec.getManche()+1));
						selec.setPerte((selec.getPert()+1));
					}else{
						Log.d("Essai",  "gainclickfalse"+selec.getVict());
						selec.setVictoire(0);
						selec.setPerte(0);
						selec.setManche(1);
						mView.s=true;
					}
					b.change=true;
					b.level=selec.getLevel();
					mView.force=selec.getForce(selec.getLevel());
					selec.setManche(selec.getManche());					
					mDetecteur.reset();
					mDetecteur.resume();
				}
			});

			break;
		}
		Log.d("Essai",  "build");
		
		return builder.create();
	}

	@Override
	public void onPrepareDialog (int id, Dialog box) {
		Log.d("Essai",  "prepare");
		mDetecteur.stop();
	}
}
