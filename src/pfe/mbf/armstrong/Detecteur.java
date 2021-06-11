package pfe.mbf.armstrong;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class Detecteur {

	private SensorManager mManager = null;
	private Variable brasJoueur;
	private Sensor orientation = null;
	private Jeux mSurface = null;
	public static float  sns,snb,snz;
	
	public void setVariable(Variable bras)
	{
		this.brasJoueur = bras;
	}
	
	public Variable getVariable()
	{
		Log.d("detecteur", "braGet"+brasJoueur);
		return this.brasJoueur;
		
	}
	@SuppressWarnings("deprecation")
	public Detecteur(Jeux pView) {
		Log.d("detecteur", "detecteur");

		mSurface = pView;
		mManager = (SensorManager) mSurface.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
		orientation = mManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	
	}
	
	SensorEventListener mSensorEventListener = new SensorEventListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onSensorChanged(SensorEvent pEvent) {
	
			sns = pEvent.values[0];
			snb = pEvent.values[1];
			snz = pEvent.values[2];
	
			if((sns>240)&&(sns<359)){
				sns=240;
			}
			if((sns<60)&&(sns>=0)){
				sns=60;
			}
			brasJoueur.rotation=sns;
			brasJoueur.setForce(brasJoueur.getForce());
			if((brasJoueur.getForce()<=1)&&(brasJoueur.getForce()>-30)){
				mSurface.showDialog(Jeux.DEFEAT_DIALOG);
			}else if((brasJoueur.getForce()>=180)&&(brasJoueur.getForce()<=290)){
				mSurface.showDialog(Jeux.VICTORY_DIALOG);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {
		

	}
	};
	public void reset() {
		Log.d("detecteur", "reset");
		brasJoueur.reset();
	} 
	public void stop() {
		Log.d("detecteur", "stop");
		mManager.unregisterListener(mSensorEventListener, orientation);
	}
	
	public void pause(){
		Log.d("detecteur", "pause");
		mManager.unregisterListener(mSensorEventListener, orientation);

	}
	public void resume() {
		Log.d("detecteur", "resume");

		mManager.registerListener(mSensorEventListener, orientation, SensorManager.SENSOR_DELAY_GAME);
	}


}
