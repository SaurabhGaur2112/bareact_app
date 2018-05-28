package com.example.gaursaurabh.bareact.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Saurabh Gaur on 9/25/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String dbname = "bareact.db";
    public static int dbversion = 1;
    public static String table = "favourites";
    public static String column1 = "section";
    public static String createTable = "create table favourites(_id integer primary key autoincrement,section text)";
    SQLiteDatabase db;

    public DBHelper(Context context){
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addFavourites(String sectionId)
    {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column1, sectionId);

        db.insert(table,null,values);
        db.close();
    }

    public ArrayList<String> showSingle()
    {
        db = getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor c = db.rawQuery("select section from favourites order by section",null);
        while(c.moveToNext())
        {
            String section_value = c.getString(1);
            arrayList.add(section_value);
        }
        db.close();
        return arrayList;
    }

    public String showSingle(String secId)
    {
        db = getWritableDatabase();
        String[] arr = {secId};
//        String query = "Select section from favourites where section "+secId;
//        Cursor data = db.rawQuery(query,null);
        String[] col1 = {"section"};
//        Cursor c = db.query(table,col1,secId,null,null,null,null);
        Cursor c = db.query(table,col1,"section=?",arr,null,null,null);
        c.moveToNext();
//        Cursor c = db.rawQuery("select section from favourites order by _id where section=?",arr);
        return c.getString(0);
    }

    public void deleteFav(String secId)
    {
        db = getWritableDatabase();
        String str[] = {secId};
        db.delete(table,"section=?",str);
        db.close();
    }

    public String checkIfExist(String secId)
    {
        db = getReadableDatabase();
        String[] arr = {secId};
        String[] col1 = {"section"};
        Cursor cursor = db.query(table,col1,"section=?",arr,null,null,null);
        if (cursor.getCount() > 0)
            return "true";
        else
            return "false";
    }

    public String[] getFavourites(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT section FROM favourites", null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("section")));
            cursor.moveToNext();
        }
        cursor.close();
        return names.toArray(new String[names.size()]);
    }
}
