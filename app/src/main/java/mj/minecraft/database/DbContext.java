package mj.minecraft.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import mj.minecraft.servertracker.model.StoredServer;

public class DbContext {

    private static final String TAG = "DbContext";
    private static DbContext sInstance = null;
    private static DbHelper db;

    public static DbContext getInstance(Context context) {
        if( sInstance == null ){
            sInstance = new DbContext();
            sInstance.initDb(context);
        }
        return sInstance;
    }

    private void initDb(Context context){
        db = new DbHelper(context);
    }

    public DbHelper getDb(){
        return db;
    }

    public ArrayList<StoredServer> getAllDataAsObjectList(){
        ArrayList<StoredServer> selectedGenres = new ArrayList<StoredServer>();
        Cursor res = db.getAllData();


        if(res.getCount() == 0){
            Log.e(TAG, "getAllDataAsObjectList: Error occurred while obtaining data from the database or the table is empty." );
            return null;
        }else{


            //StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                StoredServer ss = new StoredServer();
                ss.ipAddress = res.getString(0);
                ss.trackingInterval = Integer.parseInt( res.getString(1) );
                ss.isTracked = res.getString(2).equals("1");
                selectedGenres.add(ss);
            }
            return selectedGenres;
        }
    }


}
