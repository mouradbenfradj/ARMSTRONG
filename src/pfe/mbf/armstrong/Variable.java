package pfe.mbf.armstrong;

import android.app.Activity;
import android.util.Log;

public class Variable extends Activity {
	private int force;
	private int rapiditer;
 
	public int level,k;
	public float rotation;
	boolean rot,change;
	
	
	
	
	public Variable(){
	force=90;
	rapiditer=-90;
	this.rot=false;
	this.change=true;
	}
	
	public int getRapiditer() {
		return this.rapiditer;
	}
	
	
	public void setRapiditer(int i) {
		if(this.rot){
			this.rapiditer = i+k;
			if(this.rapiditer>90){
				this.rot=false;
			}
		}
		else{ 
				this.rapiditer = i-k;
				if(this.rapiditer<-90) {
					this.rot=true;
				}
			}
				
		}

	
	
	public int getForce() {
		return this.force;
	}
	public void setForce(int i) {
		if(i<45){
		if(!Jeux.music2.isPlaying()){
			Jeux.music2.start();
		}
		}
		if(i<1){
		i = 1;
		}
		this.force = i;
	}

	public void reset() {
		Log.d("jeux", "reset");
		force=90;
	}

	

	
}
