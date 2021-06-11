package pfe.mbf.armstrong;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Interface extends SurfaceView implements SurfaceHolder.Callback {
	
	private Variable brasJoueur;
	public Variable getVariable() {
		return this.brasJoueur;
	}
	public void setVariable(Variable pVariable) {
		this.brasJoueur = pVariable;
	}
	
	SurfaceHolder mSurfaceHolder;
	SurfaceThread mThread;
	Bitmap plant,perso,bouton,energie,brasJ,conteneur,compteur,eguit,brasa,bb,bh,mouv,passel,cache;
	int force,x,f;
	Boolean s;
	DatabaseHandler selec;
	public float hidth;
	public float width;	
	
	public Interface(Context context) {
		super(context);
		Log.d("interface", "interface");
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new SurfaceThread(); 
		initialisation(1);
		s=true;
		
	}

	public void pause(){

		mThread.miseEnMarche = false;
		mThread.interrupt();	
		while (mThread.miseEnMarche) {
			try {
				mThread.join();	
			} catch (InterruptedException e) {}
		}
		
Log.d("interface", "thread pause");
	}
	private void initialisation(int i) {	
		Log.d("interface", "initialisation");
		cache=BitmapFactory.decodeResource(getResources(), R.drawable.cache);
		brasJ=BitmapFactory.decodeResource(getResources(), R.drawable.brasj);
		bouton = BitmapFactory.decodeResource(getResources(), R.drawable.bouton);
		conteneur = BitmapFactory.decodeResource(getResources(), R.drawable.conteneur);
		eguit = BitmapFactory.decodeResource(getResources(), R.drawable.eguit);
		energie = BitmapFactory.decodeResource(getResources(), R.drawable.energie);
		compteur = BitmapFactory.decodeResource(getResources(), R.drawable.compteur);
		mouv = BitmapFactory.decodeResource(getResources(), R.drawable.rotator);

		switch(i){
		case 1 :
			bb = BitmapFactory.decodeResource(getResources(), R.drawable.bb1);
			bh = BitmapFactory.decodeResource(getResources(), R.drawable.bh1);
			passel = BitmapFactory.decodeResource(getResources(), R.drawable.level1);
			brasa=BitmapFactory.decodeResource(getResources(), R.drawable.bras1);	
			perso = BitmapFactory.decodeResource(getResources(), R.drawable.persol1);
			plant = BitmapFactory.decodeResource(getResources(), R.drawable.plant1);
			break;
		case 2 :
			passel = BitmapFactory.decodeResource(getResources(), R.drawable.level2);

			bb = BitmapFactory.decodeResource(getResources(), R.drawable.bb1);
			bh = BitmapFactory.decodeResource(getResources(), R.drawable.bh1);		
			brasa=BitmapFactory.decodeResource(getResources(), R.drawable.hand2);
			perso = BitmapFactory.decodeResource(getResources(), R.drawable.corps2);
			plant = BitmapFactory.decodeResource(getResources(), R.drawable.raye);
			break;
		case 3 :
			passel = BitmapFactory.decodeResource(getResources(), R.drawable.level3);
			bb = BitmapFactory.decodeResource(getResources(), R.drawable.bb1);
			bh = BitmapFactory.decodeResource(getResources(), R.drawable.bh1);
			brasa=BitmapFactory.decodeResource(getResources(), R.drawable.hand3);
			perso = BitmapFactory.decodeResource(getResources(), R.drawable.saly3);
			plant = BitmapFactory.decodeResource(getResources(), R.drawable.saly);
				break;
		case 4 :
			passel = BitmapFactory.decodeResource(getResources(), R.drawable.level4);
			bb = BitmapFactory.decodeResource(getResources(), R.drawable.bb1);
			bh = BitmapFactory.decodeResource(getResources(), R.drawable.bh1);
			brasa=BitmapFactory.decodeResource(getResources(), R.drawable.brasf);
			perso = BitmapFactory.decodeResource(getResources(), R.drawable.lapin);
			plant = BitmapFactory.decodeResource(getResources(), R.drawable.stella);
				break;
		case 5 :
			passel = BitmapFactory.decodeResource(getResources(), R.drawable.level5);
			bb = BitmapFactory.decodeResource(getResources(), R.drawable.bb1);
			bh = BitmapFactory.decodeResource(getResources(), R.drawable.bh1);
			brasa=BitmapFactory.decodeResource(getResources(), R.drawable.braskhal);
			perso = BitmapFactory.decodeResource(getResources(), R.drawable.khal);
			plant = BitmapFactory.decodeResource(getResources(), R.drawable.khalp);
			break;
		}
	}
	
	
	
	
	
	@SuppressLint("DrawAllocation")
	protected void onDraw(Canvas pCanvas) {
		conteneur = Bitmap.createScaledBitmap (conteneur, 24, 240, false);
		bouton =  Bitmap.createScaledBitmap (bouton, 42, 42,false);
		energie = Bitmap.createScaledBitmap (energie, 24, brasJoueur.getForce(), false);
		compteur = Bitmap.createScaledBitmap (compteur, 150, 70, false);
		mouv = Bitmap.createScaledBitmap (mouv, 150, 70, false);
		eguit = Bitmap.createScaledBitmap (eguit, 16, 50, false);
		brasJ = Bitmap.createScaledBitmap (brasJ,50, 150, false);
		cache=Bitmap.createScaledBitmap (cache,100, 150, false);
		switch(brasJoueur.level){
		case 1 :
			if(brasJoueur.change==true){
				initialisation(1);
				if(s){
				x=0;
				s=false;
				}
				brasJoueur.change=false;
			}	
			
			if(!Jeux.music.isPlaying()){
				Jeux.music.start();			
			}
			if(!Jeux.music1.isPlaying()){
				Jeux.music1.start();
			}
			
			passel= Bitmap.createScaledBitmap (passel, 240, 282, false);
			plant = Bitmap.createScaledBitmap (plant, 240, 282, false);
			perso = Bitmap.createScaledBitmap (perso, 216, 141, false);
			bb = Bitmap.createScaledBitmap (bb, 216, 141, false);
			bh = Bitmap.createScaledBitmap (bh, 216, 141, false);
			brasa = Bitmap.createScaledBitmap (brasa, 300, 150, false);
			
			break;
		case 2 :
			if(brasJoueur.change==true){
				initialisation(2);
				if(s){
					x=0;
					s=false;
					}
				brasJoueur.change=false;
			}

			if(!Jeux.music.isPlaying()){
				Jeux.music.start();			
			}
			if(!Jeux.music1.isPlaying()){
				Jeux.music1.start();
			}
			passel= Bitmap.createScaledBitmap (passel, 240, 282, false);
			plant = Bitmap.createScaledBitmap (plant, 240, 282, false);
			perso = Bitmap.createScaledBitmap (perso, 216, 141, false);
			bb = Bitmap.createScaledBitmap (bb, 216, 141, false);
			bh = Bitmap.createScaledBitmap (bh, 216, 141, false);
			brasa = Bitmap.createScaledBitmap (brasa, 300, 150, false);
			break;
		case 3 :
			if(brasJoueur.change==true){
			initialisation(3);
			if(s){
				x=0;
				s=false;
				}
			brasJoueur.change=false;
			}

			if(!Jeux.music.isPlaying()){
				Jeux.music.start();			
			}
			if(!Jeux.music1.isPlaying()){
				Jeux.music1.start();
			}
			passel= Bitmap.createScaledBitmap (passel, 240, 282, false);
			plant = Bitmap.createScaledBitmap (plant, 240, 282, false);
			perso = Bitmap.createScaledBitmap (perso, 216, 141, false);
			bb = Bitmap.createScaledBitmap (bb, 216, 141, false);
			bh = Bitmap.createScaledBitmap (bh, 216, 141, false);
			brasa = Bitmap.createScaledBitmap (brasa, 300, 150, false);
			break;
		case 4 :
			if(brasJoueur.change==true){
			initialisation(4);
			if(s){
				x=0;
				s=false;
				}
			brasJoueur.change=false;
			}

			if(!Jeux.music.isPlaying()){
				Jeux.music.start();			
			}
			if(!Jeux.music1.isPlaying()){
				Jeux.music1.start();
			}
			passel= Bitmap.createScaledBitmap (passel, 240, 282, false);
			plant = Bitmap.createScaledBitmap (plant, 240, 282, false);
			perso = Bitmap.createScaledBitmap (perso, 216, 141, false);
			bb = Bitmap.createScaledBitmap (bb, 216, 141, false);
			bh = Bitmap.createScaledBitmap (bh, 216, 141, false);
			brasa = Bitmap.createScaledBitmap (brasa, 300, 150, false);
			break;
		case 5 :
			if(brasJoueur.change==true){
			initialisation(5);
			if(s){
				x=0;
				s=false;
				}
		brasJoueur.change=false;
			}

			if(!Jeux.music.isPlaying()){
				Jeux.music.start();			
			}
			if(!Jeux.music1.isPlaying()){
				Jeux.music1.start();
			}
			passel= Bitmap.createScaledBitmap (passel, 240, 282, false);
			plant = Bitmap.createScaledBitmap (plant, 240, 282, false);
			perso = Bitmap.createScaledBitmap (perso, 216, 141, false);
			bb = Bitmap.createScaledBitmap (bb, 216, 141, false);
			bh = Bitmap.createScaledBitmap (bh, 216, 141, false);
			brasa = Bitmap.createScaledBitmap (brasa, 300, 150, false);
			break;
		}
		/*
		plant, 240, 282,
		conteneur, 24, 240,
		bouton, 42, 42
		energie, 24, force,
		perso, 216, 141,
		bb, 216, 141,
		bh, 216, 141,
		compteur, 150, 70,
		mouv, 150, 70, 
		eguit, 16, 50, 
		brasa, 300, 150, 
		brasJ,100, 300,
		cache 100, 150
		*/
		if(x<480){
			
			x=x+30;
			pCanvas.drawARGB(255, 0, 0, 0);
		pCanvas.drawBitmap(passel, -240+x, 0, null);		
		}else{
			if((((-150+Detecteur.sns)-brasJoueur.getRapiditer())>0)){
				f=(int)-((-150+Detecteur.sns)+(brasJoueur.getRapiditer()));
			}
			if((force-f)>0){
				f=(force-f);
			}else{
				f=-(force-f);
				if(f>5){
					f=5;
				}else{ if(f<-5){
					f=-5;
				}
					
				}
			}
			if(brasJoueur.getForce()>=1)
			{
				brasJoueur.setForce(brasJoueur.getForce()-force);
				
			}
		brasJoueur.setRapiditer(brasJoueur.getRapiditer());
		pCanvas.drawBitmap(plant, 0, 0, null);		
		pCanvas.drawBitmap(perso, 32, 121, null);

		pCanvas.drawBitmap(bb, 32, 121, null);
		pCanvas.drawBitmap(bh, 32, 121, null);
		pCanvas.drawBitmap(compteur,45 , 0, null);
		pCanvas.rotate((0+brasJoueur.getRapiditer()),120 , 70);		
		pCanvas.drawBitmap(mouv,45 , 0, null);
		pCanvas.rotate((0-brasJoueur.getRapiditer()),120, 70);
		
		pCanvas.rotate((-150+Detecteur.sns),113, 70);
		pCanvas.drawBitmap(eguit,105 , 20, null);
		pCanvas.rotate(-(-150+Detecteur.sns),113, 70);
		//(90-brasJoueur.getForce())
				pCanvas.rotate((45-brasJoueur.getForce()),90, 250);
				pCanvas.drawBitmap(brasa, 20, 120, null);
				pCanvas.rotate(-(45-brasJoueur.getForce()),90, 250);

				pCanvas.rotate((90-(brasJoueur.getForce())),90, 250);
				pCanvas.drawBitmap(brasJ,90, 150, null);
				pCanvas.rotate(-(90-brasJoueur.getForce()),90, 250);

		/*		pCanvas.rotate(-270,125, 230);
				pCanvas.drawBitmap(cache,100, 130, null);
				pCanvas.rotate(270,125, 230);
				*/
		pCanvas.rotate(180,120, 141);
		pCanvas.drawBitmap(conteneur,0, 20, null);
		pCanvas.drawBitmap(energie,0,20, null);
		pCanvas.rotate(-180,120, 141);
		pCanvas.drawBitmap(bouton, 0, 141, null);
			
		}
	}
	

	
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.miseEnMarche = true;
		mThread.start();				
		Log.d("interface", "created"+mThread.getState());
	}
	
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.d("interface", "chnage "+arg1+" "+arg2+" "+arg3);
		hidth=arg3;
		width=arg2;
			
	}

	
	@Override	
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d("interface", "destroy");

		mThread.miseEnMarche = false;
		
		mThread.interrupt();
		while (mThread.miseEnMarche) {
			try {
				mThread.join();	
			} catch (InterruptedException e) {}
		}
		Log.d("interface", "created"+mThread.getState());
	}
	
	
	
	
	
	
	
	
	private class SurfaceThread extends Thread {
		
		boolean miseEnMarche = true;
		@Override
		public void run() {
			Log.d("interface", "run");

			Canvas canvas;
			while (miseEnMarche) {
				canvas = null;
				try {
					canvas = mSurfaceHolder.lockCanvas();
					synchronized (mSurfaceHolder) {
					onDraw(canvas);
					}
				} finally {
					if (canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}

			}
		}
	}
}
