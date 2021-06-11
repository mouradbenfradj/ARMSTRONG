package pfe.mbf.armstrong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler{
	
	private static final String NOMBASE = "armstong.db";
	private static final int VERTION=1;
	
	private static final String NOMTABLE1 = "historique";
	private static final String COLTAB11 = "id";
	private static final String COLTAB12 = "victoir";
	private static final String COLTAB13 = "perte";
	private static final String COLTAB14 = "level";
	private static final String COLTAB15 = "manch";
	
	public static final String historique ="CREATE TABLE "+NOMTABLE1+" ("+
	COLTAB11+" INTEGER PRIMARY KEY, " +
	COLTAB12+" INTEGER, " +
	COLTAB13+" INTEGER, " +
	COLTAB14+" INTEGER, " +
	COLTAB15+" INTEGER);";
	
	private static final String NOMTABLE2 = "level";
	private static final String COLTAB21 = "id_level";
	private static final String COLTAB22 = "force";
	private static final String COLTAB23 = "endurance";
	private static final String COLTAB24 = "rapiditer";
	private static final String COLTAB25 = "technique";
	private static final String COLTAB26 = "psychologie";
	private static final String COLTAB27 = "charme";
	private static final String COLTAB28 = "nom";

	
	public static final String level ="CREATE TABLE "+NOMTABLE2+" (" +
	COLTAB21 +" INTEGER PRIMARY KEY, " +
	COLTAB22 +" INTEGER, " +
	COLTAB23 +" INTEGER, " +
	COLTAB24 +" INTEGER, " +
	COLTAB25 +" INTEGER, " +
	COLTAB26 +" INTEGER, " +
	COLTAB27 +" INTEGER, " +
	COLTAB28 +" TEXT);";

	public static final String supression1 = "DROP TABLE IF EXISTS "+NOMTABLE1;	
	public static final String supression2 = "DROP TABLE IF EXISTS "+NOMTABLE2;
	
	private Basedonnee base;
	private final Context contex;
	private SQLiteDatabase sqldb;
	public class Basedonnee extends SQLiteOpenHelper {

		public Basedonnee(Context context) {
			super(context, NOMBASE, null, VERTION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL(historique);
			db.execSQL(level);
			db.execSQL("INSERT or replace INTO "+NOMTABLE2+" ("+COLTAB21+", "+COLTAB22+", "+COLTAB23+", "+COLTAB24+", "+COLTAB25+", "+COLTAB26+", "+COLTAB27+", "+COLTAB28+") VALUES(1,1,1,1,1,2,1,'bob texas');");
			db.execSQL("INSERT or replace INTO "+NOMTABLE2+" ("+COLTAB21+", "+COLTAB22+", "+COLTAB23+", "+COLTAB24+", "+COLTAB25+", "+COLTAB26+", "+COLTAB27+", "+COLTAB28+") VALUES(2,2,1,2,2,2,2,'Raye');");
			db.execSQL("INSERT or replace INTO "+NOMTABLE2+" ("+COLTAB21+", "+COLTAB22+", "+COLTAB23+", "+COLTAB24+", "+COLTAB25+", "+COLTAB26+", "+COLTAB27+", "+COLTAB28+") VALUES(3,3,1,3,3,6,3,'saly');");
			db.execSQL("INSERT or replace INTO "+NOMTABLE2+" ("+COLTAB21+", "+COLTAB22+", "+COLTAB23+", "+COLTAB24+", "+COLTAB25+", "+COLTAB26+", "+COLTAB27+", "+COLTAB28+") VALUES(4,4,2,4,4,6,4,'stella');");
			db.execSQL("INSERT or replace INTO "+NOMTABLE2+" ("+COLTAB21+", "+COLTAB22+", "+COLTAB23+", "+COLTAB24+", "+COLTAB25+", "+COLTAB26+", "+COLTAB27+", "+COLTAB28+") VALUES(5,5,3,5,5,6,5,'khal');");
			db.execSQL("INSERT or replace INTO "+NOMTABLE1+" ("+COLTAB11+", "+COLTAB12+", "+COLTAB13+", "+COLTAB14+", "+COLTAB15+") VALUES(1,1,1,1,1);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d("base", "update");
			db.execSQL(supression1);
			db.execSQL(supression2);
			onCreate(db);		
		}
	
	}
	
	public DatabaseHandler(Context c){
		contex = c;
	}
	
	public DatabaseHandler ouverture(){
		base = new Basedonnee(contex);
		sqldb = base.getWritableDatabase();
		return this;
	}
	public void fermer(){
		base.close();
	}

	
	public int getForce(int i) {
		Cursor c = sqldb.rawQuery("SELECT "+COLTAB22+" FROM "+NOMTABLE2+" WHERE "+COLTAB21+" = "+i+";", null);
		c.moveToFirst();
		int fors =c.getInt(0);
		c.close();
		return  fors;
	}
	
	
	public int getVict() {
		Cursor c = sqldb.rawQuery("SELECT " + COLTAB12 + " FROM " + NOMTABLE1 +";", null);
		c.moveToFirst();
		int i =c.getInt(0);	
		c.close();	
		return  i;
	}
	public void setVictoire(int i) {
		ContentValues cv2 = new ContentValues();
		cv2.put(COLTAB12,i);
		sqldb.update(NOMTABLE1,cv2, null, null);	
	}
	public int getPert() {
		Cursor c = sqldb.rawQuery("SELECT " + COLTAB13 + " FROM " + NOMTABLE1 +";", null);
		c.moveToFirst();
		int i =c.getInt(0);
		c.close();
		return  i;
	}
	
	
	public void setPerte(int i) {
		ContentValues cv2 = new ContentValues();
		cv2.put(COLTAB13,i);
		sqldb.update(NOMTABLE1,cv2, null, null);	
	}
	

	public int getLevel() {
		Cursor c = sqldb.rawQuery("SELECT " + COLTAB14 + " FROM " + NOMTABLE1 +";", null);
		c.moveToFirst();
		int lev =c.getInt(0);
		c.close();
		return  lev;
	}

	public void updateLevel(int i) {
		ContentValues cv = new ContentValues();
		if(i>5){i=1;}
		cv.put(COLTAB14,i);
		sqldb.update(NOMTABLE1,cv ,null , null);
	}
	
	public int getManche() {
		Cursor c = sqldb.rawQuery("SELECT " + COLTAB15 + " FROM " + NOMTABLE1 +";", null);
		c.moveToFirst();
		int man =c.getInt(0);
		c.close();
		return  man;
	}

	public void setManche(int i) {
	ContentValues cv = new ContentValues();
	cv.put(COLTAB15,i);
	Log.d("base", "mancheset"+i);
	sqldb.update(NOMTABLE1,cv, null, null);
	}

	public void res() {
		ContentValues cv = new ContentValues();
		cv.put(COLTAB11,1);
		cv.put(COLTAB12,1);
		cv.put(COLTAB13,1);
		cv.put(COLTAB14,1);
		cv.put(COLTAB15,1);
		sqldb.update(NOMTABLE1,cv ,null , null);			
	}

	public int getRapiditer(int level2) {
		Cursor c = sqldb.rawQuery("SELECT "+COLTAB24+" FROM "+NOMTABLE2+" WHERE "+COLTAB21+" = "+level2+";", null);
		c.moveToFirst();
		int fors =c.getInt(0);
		c.close();
		return  fors;
	}

}


