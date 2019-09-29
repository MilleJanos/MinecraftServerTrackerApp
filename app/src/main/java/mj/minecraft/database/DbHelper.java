package mj.minecraft.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DbHelper";
    public static final String DATABASE_NAME = "McServerTracker.db";
    public static final String TABLE_NAME = "saved_servers";
    public static final String COLUMN_SERVER_KEY = "server_ip";
    public static final String COLUMN_TRACKING_INTERVAL_KEY = "tracking_interval";
    public static final String COLUMN_IS_TRACKED_KEY = "is_tracked";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1); // create database
        //SQLiteDatabase db = this.getWritableDatabase(); // create database & table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + COLUMN_SERVER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TRACKING_INTERVAL_KEY + " INTEGER,"
                + COLUMN_IS_TRACKED_KEY + " BOOL"
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int genre_id, boolean selected){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRACKING_INTERVAL_KEY, genre_id);
        contentValues.put(COLUMN_IS_TRACKED_KEY, selected);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public long replaceData(int genre_id, boolean selected){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRACKING_INTERVAL_KEY, genre_id);
        contentValues.put(COLUMN_IS_TRACKED_KEY, selected);
        long result = db.replace(TABLE_NAME,null,contentValues);
        return result; // id of the row | -1 if failed
    }

    public void deleteData(int genre_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_TRACKING_INTERVAL_KEY+ " = ?", new String[]{String.valueOf(genre_id)});
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"", new String[]{});
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

}
